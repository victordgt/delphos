package com.example.web;

import com.example.GreetingQueries;
import com.example.Repository;
import com.example.jdo.PersistenceManagerFilter;
import com.example.model.Greeting;
import com.example.service.JdoGreetingQueries;
import com.example.service.JdoGreetingRepository;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * This Guice module sets up the bindings used in this Wicket application, including the
 * JDO PersistenceManager.
 */
public class GuiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        // Enable per-request-thread PersistenceManager injection.
        install(new PersistenceManagerFilter.GuiceModule());

        // Business object bindings go here.
        bind(GreetingQueries.class).to(JdoGreetingQueries.class);
        bind(new TypeLiteral<Repository<Greeting>>() { }).to(JdoGreetingRepository.class);
    }
}
