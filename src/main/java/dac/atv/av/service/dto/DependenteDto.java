package dac.atv.av.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteDto {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;

}
