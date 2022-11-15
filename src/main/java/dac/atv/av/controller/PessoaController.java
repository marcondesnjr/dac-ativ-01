package dac.atv.av.controller;


import dac.atv.av.service.PessoaService;
import dac.atv.av.service.dto.DependenteDto;
import dac.atv.av.service.dto.PessoaDto;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PessoaController implements Serializable {

    @Inject
    @NotNull
    private PessoaService pessoaService;

    public PessoaDto salvarPessoa(String cpf, String nome, Long dptId) throws SQLException {
        var pessoaDto = new PessoaDto();
        pessoaDto.setCpf(cpf);
        pessoaDto.setNome(nome);
        var dependenteDto = new DependenteDto();
        dependenteDto.setId(dptId);
        pessoaDto.setDependente(dependenteDto);
        pessoaService.salvarPessoa(pessoaDto);
        return pessoaDto;
    }

    public List<PessoaDto> listarTodasPessoas() throws SQLException {
        return pessoaService.listarPessoas().stream().toList();
    }

    public PessoaDto lerPessoa(Long id) throws SQLException {
        return pessoaService.lerPessoa(id);
    }

    public PessoaDto atualizarPessoa(Long id, String cpf, String nome, Long dptId) throws SQLException {
        var pessoaDto = new PessoaDto();
        pessoaDto.setId(id);
        pessoaDto.setCpf(cpf);
        pessoaDto.setNome(nome);
        var dependenteDto = new DependenteDto();
        dependenteDto.setId(dptId);
        pessoaDto.setDependente(dependenteDto);
        return pessoaService.updatePessoa(pessoaDto);
    }

    public void excluirPessoa(Long id) throws SQLException {
        pessoaService.removerPessoa(id);
    }

    public List<PessoaDto> listarTodasPessoasByCpf(String cpf) throws SQLException {
        return pessoaService.listarPessoasByCpf(cpf).stream().toList();
    }


}
