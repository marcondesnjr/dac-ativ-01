package dac.atv.av.repository.impl;

import dac.atv.av.model.Dependente;
import dac.atv.av.repository.DependenteRepository;
import jakarta.inject.Named;

import java.util.Collection;

@Named
public class DependenteEmJDBC implements DependenteRepository {

    @Override
    public Dependente create(Dependente entity) {
        //TODO
        return null;
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
