package dac.atv.av.view.pessoa;

import dac.atv.av.controller.PessoaController;
import dac.atv.av.service.dto.PessoaDto;
import jakarta.annotation.PostConstruct;
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

@Named
@Getter @Setter
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@ViewScoped
public class ListPessoaView implements Serializable {

    @NotNull
    @Inject
    private PessoaController pessoaController;

    private List<PessoaDto> pessoaDtoList;
    private String campoBusca;

    @PostConstruct
    private void init() throws SQLException {
        updatePessoaList();
    }

    public void removePessoa(Long id) throws SQLException {
        pessoaController.excluirPessoa(id);
        updatePessoaList();
    }

    private void updatePessoaList() throws SQLException {
        if(campoBusca !=null && !campoBusca.isBlank()){
            filtrar();
        }else {
            this.pessoaDtoList = pessoaController.listarTodasPessoas();
        }
    }

    public void filtrar() throws SQLException {
        if(campoBusca == null || campoBusca.isBlank()){
            updatePessoaList();
        }else{
            this.pessoaDtoList = pessoaController.listarTodasPessoasByCpf(campoBusca);
        }
    }


}