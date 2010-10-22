package com.example;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * This base test class sets up a simulated Google App Engine environment.  Development-mode GAE services are made
 * available to tests through .
 */
public abstract class BaseGoogleAppEngineTest
{
    protected final LocalServiceTestHelper appEngineHelper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    protected PersistenceManagerFactory persistenceManagerFactory;

    @BeforeClass
    public void createPersistenceManagerFactory()
    {
        persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    }

    @AfterClass
    public void closePersistenceManagerFactory()
    {
        persistenceManagerFactory.close();
    }

    @BeforeMethod
    public final void setUpAppEngine() throws Exception
    {
        appEngineHelper.setUp();
        appEngineHelper.setEnvAuthDomain("");

        // prevents the exception that complains about creating more than one PersistenceManagerFactory
        System.setProperty("appengine.orm.disable.duplicate.pmf.exception", Boolean.TRUE.toString());
    }

    @AfterMethod
    public final void tearDownAppEngine() throws Exception
    {
        appEngineHelper.tearDown();
    }
}
