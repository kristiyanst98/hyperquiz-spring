package hyperquiz.dao.impl;

import hyperquiz.dao.KeyGenerator;
import hyperquiz.dao.Repository;
import hyperquiz.exceptions.EntityAlreadyExistsException;
import hyperquiz.exceptions.EntityCreationException;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.model.Identifiable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K, V> {
    private Map<K, V> entities = new ConcurrentHashMap<>();
    private KeyGenerator<K> keyGenerator;

    public RepositoryMemoryImpl() {
    }

    public RepositoryMemoryImpl(KeyGenerator<K> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) throws EntityAlreadyExistsException {

            if (keyGenerator != null) {
                if (entity.getId() == null) {
                    entity.setId(keyGenerator.getNextId());
                } else {
                    if (entities.get(entity.getId()) != null) {
                        throw new EntityAlreadyExistsException(
                                String.format("Entity with ID='%s' already exists.", entity.getId()));
                    }
                }
            }
            entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V update(V entity) throws EntityDataInvalidException {
        Optional<V> old = findById(entity.getId());
        if (old.isEmpty()) {
            throw new EntityDataInvalidException(
            String.format("Entity with ID='%s' does not exist.", entity.getId())
            );
        }

        entities.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public V deleteById(K id) throws EntityDataInvalidException {
//        Optional<V> old = findById(id);
//        if(old.isEmpty()) {
//            throw new EntityNotFoundException(
//                    String.format("Entity with ID='%s' does not exist.", id));
//        }
        V old = entities.remove(id);
        if (old == null) {
            throw new EntityDataInvalidException(
                    String.format("Entity with ID='%s' does not exist.", id));
        }
        return old;
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public List<V> createBatch(Collection<V> collection)throws EntityCreationException {
        return null;
    }
}
