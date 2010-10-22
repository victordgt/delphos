package com.example.jdo;

import com.google.inject.Provider;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This base class supplies convenient methods for subclasses to implement JDO queries.
 * @param <T> the persistent entity type
 */
public abstract class JdoQueries<T>
{
    private final Class<T> clazz;
    private final Provider<PersistenceManager> pmProvider;

    protected JdoQueries(Class<T> clazz, Provider<PersistenceManager> pmProvider)
    {
        this.clazz = clazz;
        this.pmProvider = pmProvider;
    }

    protected Query newQuery()
    {
        return pmProvider.get().newQuery(clazz);
    }

    @SuppressWarnings("unchecked")
    protected Collection<T> toCollection(Object queryResult)
    {
        return (Collection<T>) queryResult;
    }

    protected List<T> toList(Object queryResult)
    {
        return new ArrayList<T>(toCollection(queryResult));
    }
}
