package hyperquiz.dao;

@FunctionalInterface // SAM
public interface KeyGenerator<K> {
    K getNextId();
}
