package dac.atv.av.model;

import lombok.Data;

@Data
public class Pessoa {
    private Long id;
    private String cpf;
    private String nome;
    private Dependente dependente;
}
