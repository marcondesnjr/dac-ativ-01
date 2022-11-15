package dac.atv.av.view.pessoa;

import dac.atv.av.controller.DependenteController;
import dac.atv.av.controller.PessoaController;
import dac.atv.av.service.dto.DependenteDto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
@Getter @Setter
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class EditPessoaView implements Serializable {

    @NotNull
    @Inject
    private PessoaController pessoaController;
    @NotNull
    @Inject
    private DependenteController dependenteController;

    private long id;
    private String cpf;
    private String nome;
    private Long dptId;
    private List<DependenteDto> dependenteDtoList;

    @PostConstruct
    private void init() throws SQLException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(!params.containsKey("id")){
            return;
        }
        long requestId = Long.parseLong(params.get("id"));
        var pessoaDto = pessoaController.lerPessoa(requestId);
        this.id = pessoaDto.getId();
        this.cpf = pessoaDto.getCpf();
        this.nome = pessoaDto.getNome();
        this.dptId = pessoaDto.getDependente().getId();
        updateDependenteList();
    }

    public void updatePessoa() throws SQLException {
        pessoaController.atualizarPessoa(this.id, this.cpf, this.nome, this.dptId);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Dados Salvos", null));
    }

    private void updateDependenteList() throws SQLException {
        this.dependenteDtoList = dependenteController.listarTodosDependentes();
    }


}