package dac.atv.av.repository.impl;

import dac.atv.av.model.Pessoa;
import dac.atv.av.repository.PessoaRepository;
import jakarta.inject.Named;

import java.util.Collection;

@Named
public class PessoasEmJDBC implements PessoaRepository {

    @Override
    public Pessoa create(Pessoa entity) {
        //TODO
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
