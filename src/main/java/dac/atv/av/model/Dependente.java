package dac.atv.av.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Dependente {

    private String id;
    private String nome;
    private LocalDate dataDeNascimento;

}
