package com.example;

/**
 * A Repository provides basic operations to manage persistent entities.
 */
public interface Repository<T>
{
    /**
     * Return a stored entity by its key.
     *
     * @param key the unique key for the entity
     * @return the entity stored by the key
     */
    public T get(Object key);

    /**
     * Persist (store) a new entity.
     *
     * @param entity the entity to persist
     */
    void persist(T entity);

    /**
     * Delete a stored entity.
     *
     * @param entity the entity to delete
     */
    void delete(T entity);

    /**
     * Executes the Runnable block within a transaction.  If block.run() executes without an exception,
     * the transaction is committed; otherwise the transaction is rolled back.
     *
     * @param block the block to run
     */
    void runInTransaction(Runnable block);
}
