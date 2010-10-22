package com.example.service;

import com.example.jdo.JdoRepository;
import com.example.model.Greeting;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.jdo.PersistenceManager;

public class JdoGreetingRepository extends JdoRepository<Greeting>
{
    @Inject
    public JdoGreetingRepository(Provider<PersistenceManager> pmProvider)
    {
        super(Greeting.class, pmProvider);
    }
}