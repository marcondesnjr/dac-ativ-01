package dac.atv.av.service.dto;

import dac.atv.av.model.Dependente;
import lombok.Data;

@Data
public class PessoaDto {
    private Long id;
    private String cpf;
    private String nome;
    private DependenteDto dependente;
}
