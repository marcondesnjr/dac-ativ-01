package dac.atv.av.service;

import dac.atv.av.model.Dependente;
import dac.atv.av.repository.DependenteRepository;
import dac.atv.av.service.dto.DependenteDto;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.sql.SQLException;

@Named
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class DependenteService implements Serializable {

    private DependenteRepository dependenteRepository;

    public DependenteDto salvarDependente(DependenteDto dependenteDto) throws SQLException {
        var dependente = new Dependente();
        dependente.setId(dependenteDto.getId());
        dependente.setNome(dependenteDto.getNome());
        dependente.setDataDeNascimento(dependenteDto.getDataDeNascimento());
        var savedDepdt = dependenteRepository.create(dependente);
        dependenteDto.setId(savedDepdt.getId());
        return dependenteDto;
    }

}
