package dac.atv.av.service;

import dac.atv.av.model.Pessoa;
import dac.atv.av.repository.DependenteRepository;
import dac.atv.av.repository.PessoaRepository;
import dac.atv.av.service.dto.DtoConverter;
import dac.atv.av.service.dto.PessoaDto;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

@Named
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PessoaService implements Serializable {

    @Inject
    @NotNull
    private PessoaRepository pessoaRepository;
    @Inject
    @NotNull
    private DependenteRepository dependenteRepository;


    public PessoaDto salvarPessoa(PessoaDto pessoaDto) throws SQLException {
        var pessoa = new Pessoa();
        pessoa.setId(pessoaDto.getId());
        pessoa.setCpf(pessoaDto.getCpf());
        pessoa.setNome(pessoaDto.getNome());
        var dependente = dependenteRepository.findById(pessoaDto.getDependente().getId())
                .orElseThrow(()-> new EntityNotFoundException("Dependente não encontrado com id: "+pessoaDto.getDependente().getId()));
        pessoa.setDependente(dependente);
        var savedPessoa = pessoaRepository.create(pessoa);
        pessoaDto.setId(savedPessoa.getId());
        return pessoaDto;
    }

    public Collection<PessoaDto> listarPessoas() throws SQLException {

        var pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(DtoConverter::toPessoaDto).toList();
    }

    public PessoaDto lerPessoa(Long id) throws SQLException {
        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado Pessoa com id: "+id));
        return DtoConverter.toPessoaDto(pessoa);
    }
    public PessoaDto updatePessoa(PessoaDto pessoaDto) throws SQLException {
        var pessoa = pessoaRepository.update(DtoConverter.toPessoa(pessoaDto))
                .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado Pessoa com id: "+pessoaDto.getId()));
        return pessoaDto;
    }

    public void removerPessoa(Long id) throws SQLException {
        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Não foi encontrado Pessoa com id: "+id));
        pessoaRepository.delete(id);
        dependenteRepository.delete(pessoa.getDependente().getId());
    }

    public Collection<PessoaDto> listarPessoasByCpf(String cpf) throws SQLException {

        var pessoas = pessoaRepository.findByCpf(cpf);
        return pessoas.stream().map(DtoConverter::toPessoaDto).toList();
    }



}
