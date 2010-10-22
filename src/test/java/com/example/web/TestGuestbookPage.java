package com.example.web;

import br.org.universa.web.DelphosApplication;

import com.example.BaseWicketGoogleAppEngineTest;
import com.example.jdo.PersistenceManagerFilter;
import com.google.inject.servlet.GuiceFilter;
import org.apache.wicket.protocol.http.WebApplication;
import org.testng.annotations.Test;

import javax.servlet.Filter;

/**
 * This is a test of the Guestbook page.
 */
public class TestGuestbookPage extends BaseWicketGoogleAppEngineTest
{
    @Override
    protected WebApplication createWebApplication()
    {
        return new DelphosApplication();
    }

    @Override
    protected Filter[] createServletFilters()
    {
        // Replicate the order of filters as defined in web.xml.
        return new Filter[] { new GuiceFilter(), new PersistenceManagerFilter() };
    }

    @Test
    public void testGuestbookPage()
    {
        final String message1 = "Testing 1..2..3";
        final String message2 = "Foobar";
        final String authorNickname = "test";
        final String authorEmail = authorNickname + "@example.com";


        appEngineHelper.setEnvEmail(authorEmail);
        appEngineHelper.setEnvIsLoggedIn(true);
        
        // Request the page and assert that it was rendered.
        tester.startPage(Guestbook.class);
        tester.assertRenderedPage(Guestbook.class);

        // Submit a message and assert that it is present.
        tester.setParameterForNextRequest("sign-form:sign-content", message1);
        tester.submitForm("sign-form");
        tester.assertRenderedPage(Guestbook.class);
        tester.assertLabel("messages:0:author", authorNickname);
        tester.assertLabel("messages:0:message", message1);

        // Submit and assert another message.
        tester.setParameterForNextRequest("sign-form:sign-content", message2);
        tester.submitForm("sign-form");
        tester.assertRenderedPage(Guestbook.class);
        tester.assertLabel("messages:0:author", authorNickname);
        tester.assertLabel("messages:0:message", message2);
        tester.assertLabel("messages:1:author", authorNickname);
        tester.assertLabel("messages:1:message", message1);
    }
}
