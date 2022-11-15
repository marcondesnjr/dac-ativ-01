package dac.atv.av.repository;

import dac.atv.av.model.Pessoa;

import java.sql.SQLException;
import java.util.Optional;

public interface PessoaRepository extends Repository<Long, Pessoa> {

    Optional<Pessoa> findByCpf(String cpf) throws SQLException;

}
