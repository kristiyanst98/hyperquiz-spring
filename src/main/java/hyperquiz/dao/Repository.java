package hyperquiz.dao;


import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityCreationException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.exceptions.EntityUpdateException;
import hyperquiz.model.Identifiable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    List<V> findAll();
    Optional<V> findById(K id) throws EntityDataInvalidException;
    V create(V entity) throws EntityAlreadyExistsException;
    V update(V entity) throws EntityUpdateException;
    V deleteById(K id) throws EntityDataInvalidException;
    long count();
    List<V> createBatch(Collection<V> collection) throws EntityCreationException;
}
