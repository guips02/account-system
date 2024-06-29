package br.com.goomie.model.account;

import lombok.Data;

@Data
public class AccountRequest {

    private String name;
    private String classification;
    private Integer code;
    private String type;
    private String nature;
    private String group;

}