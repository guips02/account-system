package br.com.goomie.repository.account;

import br.com.goomie.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// INTERFACE REPOSITORY COM OS METODOS FINDALL, FINDBYID, ETC. POR EXTENDER DE JPA REPOSITORY
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
