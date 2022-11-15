package dac.atv.av.service;

import dac.atv.av.model.Dependente;
import dac.atv.av.repository.DependenteRepository;
import dac.atv.av.service.dto.DependenteDto;
import dac.atv.av.service.dto.DtoConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

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

    public Collection<DependenteDto> listarDependentes() throws SQLException {

        var dependentes = dependenteRepository.findAll();
        return dependentes.stream().map(DtoConverter::toDependenteDto).toList();
    }

    public DependenteDto lerDependente(Long id) throws SQLException {
        var dependente = dependenteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado dependente com id: "+id));
        return DtoConverter.toDependenteDto(dependente);
    }
    public DependenteDto updateDependente(DependenteDto dependenteDto) throws SQLException {
        var dependente = dependenteRepository.update(DtoConverter.toDependente(dependenteDto))
                .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado dependente com id: "+dependenteDto.getId()));
        return dependenteDto;
    }

    public void removerDependente(Long id) throws SQLException {

        dependenteRepository.delete(id);

    }
}
