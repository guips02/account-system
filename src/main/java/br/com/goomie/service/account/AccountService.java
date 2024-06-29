package br.com.goomie.service.account;

import br.com.goomie.model.account.Account;
import br.com.goomie.model.account.AccountRequest;
import br.com.goomie.model.account.AccountResponse;
import br.com.goomie.model.account.enums.Group;
import br.com.goomie.model.account.enums.Nature;
import br.com.goomie.model.account.enums.Type;
import br.com.goomie.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountResponse findById(Long id) {
        Account account = fetchAccount(id);
        return convertToResponse(account);
    }


    public List<AccountResponse> findAll() {
        List<Account> accounts = repository.findAll();

        List<AccountResponse> result = accounts
                .stream()
                .map((account) -> convertToResponse(account))
                .toList();

        return result;
    }

    public Long create(AccountRequest obj) {
        Account account = new Account();

        BeanUtils.copyProperties(obj, account);

        account.setType(Type.valueOf(obj.getType()));
        account.setGroup(Group.valueOf(obj.getGroup()));
        account.setNature(Nature.valueOf(obj.getNature()));

        repository.save(account);

        return account.getId();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Long id, AccountRequest obj) {
        Account entity = fetchAccount(id);
        updateData(entity, obj);
        repository.save(entity);
    }

    private Account fetchAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não existente"));
    }

    private void updateData(Account entity, AccountRequest request) {
        entity.setClassification(request.getClassification());
        entity.setCode(request.getCode());

        Type type = Type.valueOf(request.getType());
        Nature nature = Nature.valueOf(request.getNature());
        Group group = Group.valueOf(request.getGroup());

        entity.setType(type);
        entity.setNature(nature);
        entity.setGroup(group);
        entity.setName(request.getName());
    }

    private AccountResponse convertToResponse(Account source) {
        AccountResponse response = new AccountResponse();
        BeanUtils.copyProperties(source, response);
        response.setType(source.getType().getLabel());
        response.setGroup(source.getGroup().getLabel());
        response.setNature(source.getNature().getLabel());
        return response;
    }

}
