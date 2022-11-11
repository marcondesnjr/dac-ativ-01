package dac.atv.av.repository;

import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T,E> {

    E create(E entity) throws SQLException;
    E findById(T id);
    Collection<E> findAll();
    E update(E entity);
    void delete(T id);

}
