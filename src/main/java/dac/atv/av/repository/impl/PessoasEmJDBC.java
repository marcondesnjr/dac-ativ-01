package dac.atv.av.repository.impl;

import dac.atv.av.model.Pessoa;
import dac.atv.av.repository.PessoaRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Collection;

@Named
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class PessoasEmJDBC implements PessoaRepository {

    @Inject
    private DataSource dataSource;



    @Override
    public Pessoa create(Pessoa entity) {
        //TODO
        QueryRunner qr = new QueryRunner(dataSource);
        return null;
    }

    @Override
    public Pessoa findById(Long id) {
        //TODO
        return null;
    }

    @Override
    public Collection<Pessoa> findAll() {
        //TODO
        return null;
    }

    @Override
    public Pessoa update(Pessoa entity) {
        //TODO
        return null;
    }

    @Override
    public void delete(Long id) {
        //TODO
    }
}
