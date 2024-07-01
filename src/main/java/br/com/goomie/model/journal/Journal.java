package br.com.goomie.model.journal;

import br.com.goomie.model.account.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_journal_entries")
@Data
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    // TODO REVISAR A ASSOCIACAO COM CONTA
    @ManyToOne
    @JoinColumn(name = "debt_account_id", nullable = false)
    private Account debtAccount;

    // TODO REVISAR A ASSOCIACAO COM CONTA
    @ManyToOne
    @JoinColumn(name = "credit_account_id", nullable = false)
    private Account creditAccount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal value;

    private String document;
}
