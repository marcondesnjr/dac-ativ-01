package dac.atv.av.repository.impl;

import dac.atv.av.model.Pessoa;
import dac.atv.av.repository.DependenteRepository;
import dac.atv.av.repository.PessoaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Named
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class PessoasEmJDBC implements PessoaRepository {

    @Inject
    private DataSource dataSource;


    @Override
    public Pessoa create(Pessoa entity) throws SQLException {
        String sql = "insert into pessoa (cpf, nome, dependente_id)\n" +
                "values (?,?,?);";

        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        QueryRunner qr = new QueryRunner(dataSource);
        Integer id = qr.insert(sql, scalarHandler, entity.getCpf(), entity.getNome(), entity.getDependente().getId());
        entity.setId(id.longValue());
        return entity;
    }

    @Override
    public Optional<Pessoa> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE id = ?";
        QueryRunner qr = new QueryRunner(dataSource);
        return qr.query(sql, new PessoaResultSetHandler(), id)
                .stream().findFirst();
    }

    @Override
    public Collection<Pessoa> findAll() throws SQLException {
        String sql = "SELECT * FROM PESSOA";
        QueryRunner qr = new QueryRunner(dataSource);
        return qr.query(sql, new PessoaResultSetHandler())
                .stream().toList();
    }

    @Override
    public Optional<Pessoa> update(Pessoa entity) throws SQLException {
        String sql = "UPDATE pessoa " +
                "SET cpf=?, nome=?, dependente_id=?" +
                "WHERE id=?";

        QueryRunner qr = new QueryRunner(dataSource);
        int lines = qr.update(sql, entity.getCpf(), entity.getNome(), entity.getId(), entity.getDependente().getId());
        if(lines == 0){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM PESSOA WHERE id = ?";

        QueryRunner qr = new QueryRunner(dataSource);
        int lines = qr.execute(sql, id);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM PESSOA WHERE cpf = ?";
        QueryRunner qr = new QueryRunner(dataSource);
        return qr.query(sql, new PessoaResultSetHandler(), cpf)
                .stream().findFirst();
    }


    @Named
    @Getter
    @Setter
    private static class PessoaResultSetHandler implements ResultSetHandler<List<Pessoa>>{

        @Inject
        private DependenteRepository dependenteRepository;

        @Override
        public List<Pessoa> handle(ResultSet resultSet) throws SQLException {
            List<Pessoa> pessoasList = new ArrayList<>();
            while (resultSet.next()){
                var pessoa = new Pessoa();
                pessoa.setId(resultSet.getLong("id"));
                pessoa.setNome(resultSet.getString("nome"));
                var dptId = resultSet.getLong("dependente_id");
                var dpt = dependenteRepository.findById(dptId)
                        .orElseThrow(() -> new SQLException("Não foi encontrado dependente com id: "+ dptId));
                pessoa.setDependente(dpt);
                pessoasList.add(pessoa);
            }
            return pessoasList;
        }
    }
}
