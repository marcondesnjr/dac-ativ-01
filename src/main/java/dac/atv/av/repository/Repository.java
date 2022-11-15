package dac.atv.av.repository;

import dac.atv.av.model.Dependente;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Repository<T,E> {

    E create(E entity) throws SQLException;
    Optional<E> findById(T id) throws SQLException;
    Collection<E> findAll() throws SQLException;
    Optional<E> update(E entity) throws SQLException;
    void delete(T id) throws SQLException;

}
