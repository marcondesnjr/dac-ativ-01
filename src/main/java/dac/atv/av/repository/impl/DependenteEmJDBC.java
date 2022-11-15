package dac.atv.av.repository.impl;

import dac.atv.av.model.Dependente;
import dac.atv.av.repository.DependenteRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import org.apache.commons.dbutils.*;
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
public class DependenteEmJDBC implements DependenteRepository {

    @Inject
    private DataSource dataSource;

    @Override
    public Dependente create(Dependente entity) throws SQLException {
        String sql = "INSERT INTO public.dependente (nome, datadenascimento) " +
                "VALUES(?, ?);";

        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        QueryRunner qr = new QueryRunner(dataSource);
        Integer id = qr.insert(sql, scalarHandler, entity.getNome(),
                entity.getDataDeNascimento());
        entity.setId(id.longValue());
        return entity;
    }

    @Override
    public Optional<Dependente> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM DEPENDENTE WHERE id = ?";
        QueryRunner qr = new QueryRunner(dataSource);
        return qr.query(sql, new DependenteResultSetHandle(), id)
                .stream().findFirst();
    }

    @Override
    public Collection<Dependente> findAll() throws SQLException {
        String sql = "SELECT * FROM DEPENDENTE";
        QueryRunner qr = new QueryRunner(dataSource);
        return qr.query(sql, new DependenteResultSetHandle());
    }

    @Override
    public Optional<Dependente> update(Dependente entity) throws SQLException {
        String sql = "UPDATE dependente SET nome = ?, datadenascimento = ? " +
                "WHERE id = ?";
        QueryRunner qr = new QueryRunner(dataSource);
        int result = qr.update(sql, entity.getNome(),entity.getDataDeNascimento(), entity.getId());
        if(result == 0){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM DEPENDENTE WHERE id = ?";
        QueryRunner qr = new QueryRunner(dataSource);
        int result = qr.execute(sql, id);
    }

    private static class DependenteResultSetHandle implements ResultSetHandler<List<Dependente>>{
        @Override
        public List<Dependente> handle(ResultSet resultSet) throws SQLException {
            List<Dependente> dependenteList = new ArrayList<>();
            while (resultSet.next()){
                var dependente = new Dependente();
                dependente.setId(resultSet.getLong("id"));
                dependente.setNome(resultSet.getString("nome"));
                dependente.setDataDeNascimento(resultSet.getDate("dataDeNascimento").toLocalDate());
                dependenteList.add(dependente);
            }
            return dependenteList;
        }
    }
}
