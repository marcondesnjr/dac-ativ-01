package dac.atv.av.view.dependente;

import dac.atv.av.controller.DependenteController;
import dac.atv.av.service.dto.DependenteDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@Named
@ViewScoped
@NoArgsConstructor
@Getter @Setter
public class EditDependenteView implements Serializable {

    @Inject
    private DependenteController dependenteController;

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;

    @PostConstruct
    private void posConstruct() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(!params.containsKey("id")){
            return;
        }
        long requestId = Long.parseLong(params.get("id"));
        var dependente = dependenteController.lerDependentes(requestId);
        this.id = dependente.getId();
        this.nome = dependente.getNome();
        this.dataDeNascimento = dependente.getDataDeNascimento();
    }


    public void editarDependente() throws SQLException {
        var dependenteDto = new DependenteDto();
        dependenteDto.setId(this.id);
        dependenteDto.setNome(this.nome);
        dependenteDto.setDataDeNascimento(this.dataDeNascimento);
        dependenteDto = dependenteController.atualizarDependente(dependenteDto);
        this.id = dependenteDto.getId();
        this.nome = dependenteDto.getNome();
        this.dataDeNascimento = dependenteDto.getDataDeNascimento();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Dados Salvos", null));
    }

}