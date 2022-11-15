package dac.atv.av.service.dto;

import dac.atv.av.model.Dependente;
import dac.atv.av.model.Pessoa;

public class DtoConverter {

    public static DependenteDto toDependenteDto(Dependente dpt){
        var dto = new DependenteDto();
        dto.setId(dpt.getId());
        dto.setNome(dpt.getNome());
        dto.setDataDeNascimento(dpt.getDataDeNascimento());
        return dto;
    }

    public static Dependente toDependente(DependenteDto dto){
        var dpt = new Dependente();
        dpt.setId(dto.getId());
        dpt.setNome(dto.getNome());
        dpt.setDataDeNascimento(dto.getDataDeNascimento());
        return dpt;
    }

    public static PessoaDto toPessoaDto(Pessoa pessoa){
        var dto = new PessoaDto();
        dto.setId(pessoa.getId());
        dto.setCpf(pessoa.getCpf());
        dto.setNome(pessoa.getNome());
        dto.setDependente(toDependenteDto(pessoa.getDependente()));
        return dto;
    }

    public static Pessoa toPessoa(PessoaDto dto){
        var pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setCpf(dto.getCpf());
        pessoa.setNome(dto.getNome());
        pessoa.setDependente(toDependente(dto.getDependente()));
        return pessoa;
    }



}
