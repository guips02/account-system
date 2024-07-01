package br.com.goomie.model.account;

import br.com.goomie.model.account.enums.Group;
import br.com.goomie.model.account.enums.Nature;
import br.com.goomie.model.account.enums.Type;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity

// CRIA UMA TABELA NO BANCO DE DADOS NOMEADA DE tb_chart_accounts
@Table(name = "tb_chart_accounts")

// JA FAZ POR BAIXO DOS PANOS O CONSTRUTOR E OS GETTERS E SETTERS
@Data
public class Account {

    // IDENTIFICA O ATRIBUTO COMO ID E CHAVE PRIMARIA (PRIMARY KEY)
    @Id
    // TORNA O ID UNICO E AUTOINCREMENTAL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // FAZ COM QUE NAO TENHA SETTER (ACCESS LEVEL = NONE)
    @Setter(AccessLevel.NONE)
    private Long id;

    // COLUNA NAO PODE SER NULO E TAMANHO DE 150 CARACTERES (VARCHAR)
    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String classification;

    @Column(nullable = false)
    private Integer code;

    // ENUMERATED REFERENCIA O ATRIBUTO PARA O ENUM CRIADO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nature nature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    // EMBORA O ATRIBUTO SEJA "group" NO BANCO SERA CHAMADO DE acc_group
    @Column(name = "acc_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private Group group;
}
