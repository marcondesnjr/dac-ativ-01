package dac.atv.av.controller.dependente;

import dac.atv.av.service.DependenteService;
import dac.atv.av.service.dto.DependenteDto;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;

@Named
@ViewScoped
@Getter @Setter
public class AddDependente implements Serializable {

    private String nome="";
    private LocalDate dataDeNascimento;

    @Inject
    private DependenteService dependenteService;
    public void salvar() throws SQLException {

        var dependenteDto = new DependenteDto();
        dependenteDto.setNome(this.nome);
        dependenteDto.setDataDeNascimento(this.dataDeNascimento);
        dependenteService.salvarDependente(dependenteDto);
        System.out.println(nome+" "+dataDeNascimento);
    }

}
