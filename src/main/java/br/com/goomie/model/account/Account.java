package br.com.goomie.model.account;

import br.com.goomie.model.account.enums.Group;
import br.com.goomie.model.account.enums.Nature;
import br.com.goomie.model.account.enums.Type;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "tb_chart_accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String classification;

    @Column(nullable = false)
    private Integer code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nature nature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(name = "acc_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private Group group;


}
