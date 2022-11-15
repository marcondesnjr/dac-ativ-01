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
import java.util.List;

@Named
@ViewScoped
@NoArgsConstructor
@Getter @Setter
public class ListarDependenteView implements Serializable {

    @Inject
    private DependenteController dependenteController;

    private List<DependenteDto> dependenteDtoList;

    @PostConstruct
    public void init() throws SQLException {
        updateDependenteList();
    }

    public void removeDependente(Long id) throws SQLException {
        try {
            dependenteController.excluirDependente(id);
            updateDependenteList();
        }catch (SQLException ex){
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Não foi possível excluir o dependente",null));
        }
    }

    private void updateDependenteList() throws SQLException {
        this.dependenteDtoList = dependenteController.listarTodosDependentes();
    }

}