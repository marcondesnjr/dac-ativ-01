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
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Getter @Setter
public class AddPessoaView implements Serializable {

    @Inject
    @NotNull
    private DependenteController dependenteController;
    @Inject
    @NotNull
    private PessoaController pessoaController;

    private String cpf;
    private String nome;
    private Long dependenteId;

    private String dpNome;
    private LocalDate dpDataNascimento;

    private List<DependenteDto> dependenteDtoList;

    @PostConstruct
    private void init() throws SQLException {
        updateDependenteList();
    }

    public void salvarDependente() throws SQLException {
        dependenteController.salvarDependente(dpNome, dpDataNascimento);
        updateDependenteList();
    }

    public void salvarPessoa() throws SQLException {
        pessoaController.salvarPessoa(this.cpf, this.cpf, this.dependenteId);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Cadastrado",null));
    }

    private void updateDependenteList() throws SQLException {
        this.dependenteDtoList = dependenteController.listarTodosDependentes();
    }

}