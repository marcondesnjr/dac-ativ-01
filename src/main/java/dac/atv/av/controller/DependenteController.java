package dac.atv.av.controller;

import dac.atv.av.service.DependenteService;
import dac.atv.av.service.dto.DependenteDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Named
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class DependenteController implements Serializable {

    @Inject
    private DependenteService dependenteService;


    public DependenteDto salvarDependente(String nome, LocalDate dataDeNascimento) throws SQLException{
        var dependenteDto = new DependenteDto();
        dependenteDto.setNome(nome);
        dependenteDto.setDataDeNascimento(dataDeNascimento);
        dependenteService.salvarDependente(dependenteDto);
        return dependenteDto;
    }

    public List<DependenteDto> listarTodosDependentes() throws SQLException {
        return dependenteService.listarDependentes().stream().toList();
    }

    public DependenteDto lerDependentes(Long id) throws SQLException {
        return dependenteService.lerDependente(id);
    }

    public DependenteDto atualizarDependente(DependenteDto dependenteDto) throws SQLException {
        return dependenteService.updateDependente(dependenteDto);
    }

    public void excluirDependente(Long id) throws SQLException {
        dependenteService.removerDependente(id);
    }

}
