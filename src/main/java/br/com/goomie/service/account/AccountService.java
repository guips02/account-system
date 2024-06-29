package br.com.goomie.service.account;

import br.com.goomie.model.account.Account;
import br.com.goomie.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account findById(Long id) {
        Account account = fetchAccount(id);
        return account;
    }


    public List<Account> findAll() {
        return repository.findAll();
    }

    public void create(Account obj) {

        if(repository.existsById(obj.getId())) {
            throw new IllegalStateException("Conta já existe.");
        }

        repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Long id, Account obj) {
        Account entity = fetchAccount(id);
        updateData(entity, obj);
        repository.save(entity);
    }

    private Account fetchAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não existente"));
    }

    private void updateData(Account entity, Account request) {
        entity.setClassification(request.getClassification());
        entity.setCode(request.getCode());
        entity.setType(request.getType());
        entity.setNature(request.getNature());
        entity.setGroup(request.getGroup());
        entity.setName(request.getName());
    }

}
