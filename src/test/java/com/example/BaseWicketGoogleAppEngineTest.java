package com.example;

import org.apache.wicket.protocol.http.WebApplication;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.servlet.Filter;

/**
 * This base test class sets up a simulated Google App Engine + Wicket environment.  Wicket pages
 * can be tested using the protected 'tester' field, and development-mode GAE services are made
 * available to tests.
 */
public abstract class BaseWicketGoogleAppEngineTest extends BaseGoogleAppEngineTest
{
    protected FilteringWicketTester tester;

    @BeforeClass
    public void setUpFilteringWicketTesterOnce()
    {
        tester = new FilteringWicketTester(createWebApplication(), createServletFilters());
    }

    @AfterClass
    public void tearDownFilteringWicketTesterOnce()
    {
        tester.destroy();
    }

    /**
     * The subclass should override this method and return new, uninitialized Filter objects, in the order
     * in which they should be executed.
     *
     * @return the servlet filters; the default implementation returns an empty array
     */
    protected Filter[] createServletFilters()
    {
        return new Filter[0];
    }

    /**
     * The subclass must return a new WebApplication subclass representing the Wicket application being tested.
     *
     * @return the WebApplication instance
     */
    protected abstract WebApplication createWebApplication();
}