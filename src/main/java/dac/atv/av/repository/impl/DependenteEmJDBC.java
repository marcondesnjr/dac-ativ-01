package dac.atv.av.repository.impl;

import dac.atv.av.model.Dependente;
import dac.atv.av.repository.DependenteRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;

@Named
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class DependenteEmJDBC implements DependenteRepository {

    private DataSource dataSource;

    @Override
    public Dependente create(Dependente entity) throws SQLException {
        String sql = "INSERT INTO public.dependente (nome, datadenascimento) " +
                "VALUES(?, ?);";

        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        QueryRunner qr = new QueryRunner(dataSource);
        long id = qr.insert(sql, scalarHandler, entity.getNome(),
                entity.getDataDeNascimento());
        entity.setId(id);
        return entity;
    }

    @Override
    public Dependente findById(Long id) {
        //TODO
        return null;
    }

    @Override
    public Collection<Dependente> findAll() {
        //TODO
        return null;
    }

    @Override
    public Dependente update(Dependente entity) {
        //TODO
        return null;
    }

    @Override
    public void delete(Long id) {
        //TODO
    }
}
