package com.example;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * This is a WicketTester that first invokes a chain of servlet filters, exactly the way they would
 * be invoked within an application server, before processing the page request.  This more closely
 * simulates the request process and is necessary to get full support of dependency injection frameworks
 * (such as Guice Servlet Extensions) that require a filter.
 */
public class FilteringWicketTester extends WicketTester
{
    private List<Filter> servletFilters;

    public FilteringWicketTester(WebApplication application, Filter... servletFilters)
    {
        super(application);
        this.servletFilters = Arrays.asList(servletFilters);
        initFilters();
    }

    @Override
    public <C extends Page> void processRequestCycle(final Class<C> pageClass, final PageParameters params)
    {
        try
        {
            // invoke the filter chain and then...
            doFilter(servletFilters.iterator(), new Runnable()
            {
                public void run()
                {
                    // ...execute the page request
                    FilteringWicketTester.super.processRequestCycle(pageClass, params);
                }
            });
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (ServletException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void submitForm(final String path)
    {
        try
        {
            // invoke the filter chain and then...
            doFilter(servletFilters.iterator(), new Runnable()
            {
                public void run()
                {
                    // ...execute the form submission
                    FilteringWicketTester.super.submitForm(path);
                }
            });
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (ServletException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy()
    {
        for (Filter filter : servletFilters)
        {
            filter.destroy();
        }

        // This insures that this instance cannot be reused.
        servletFilters = null;

        super.destroy();
    }

    private void initFilters()
    {
        try
        {
            for (final Filter filter : servletFilters)
            {
                filter.init(new FilterConfig()
                {
                    public String getFilterName()
                    {
                        return "Filter of type " + filter.getClass().getName();
                    }

                    public ServletContext getServletContext()
                    {
                        return getServletSession().getServletContext();
                    }

                    public String getInitParameter(String name)
                    {
                        return null;
                    }

                    public Enumeration getInitParameterNames()
                    {
                        return null;
                    }
                });
            }
        }
        catch (ServletException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void doFilter(final Iterator<Filter> filterIterator,
                          final Runnable processRequestCycle) throws ServletException, IOException
    {
        if (filterIterator.hasNext())
        {
            // execute the next filter and set up the chain for the next call to this method
            Filter filter = filterIterator.next();
            filter.doFilter(getServletRequest(), getServletResponse(), new FilterChain()
            {
                public void doFilter(ServletRequest request, ServletResponse response)
                        throws IOException, ServletException
                {
                    // recursively call this method again
                    FilteringWicketTester.this.doFilter(filterIterator, processRequestCycle);
                }
            });
        }
        else
        {
            // no more filters
            processRequestCycle.run();
        }
    }
}
