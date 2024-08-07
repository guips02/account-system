package br.com.goomie.model.account;

import lombok.Data;

// CLASSE DE RESPOSTA (DTO)
@Data
public class AccountResponse {
    private Long id;
    private String name;
    private String classification;
    private Integer code;
    private String type;
    private String nature;
    private String group;
}
