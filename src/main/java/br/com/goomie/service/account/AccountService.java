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

import java.util.List;

// CLASSE DE SERVICOS DE ACCOUNT, QUE POSSUI DEPENDENCIA DO REPOSITORY (AccountRepository(linha 23))
@Service

// CONSTRUTOR SO PRA QUEM POSSUI "final" (linha 24, e.g)
@RequiredArgsConstructor
public class AccountService {

    // DEPENDENCIA DE AccountRepository
    private final AccountRepository repository;

    // RETORNA CONTA POR ID UTILIZANDO OS METODOS (fetchAccount e convertToResponse)
    public AccountResponse findById(Long id) {
        Account account = fetchAccount(id);
        return convertToResponse(account);
    }

    // RETORNA TODAS AS CONTAS
    public List<AccountResponse> findAll() {
        List<Account> accounts = repository.findAll();

        // LISTA DE AccountResponse
        List<AccountResponse> result = accounts
                .stream() // USADO PARA UTILIZAR O MAP, FITER REDUCE...
                .map((account) -> convertToResponse(account))
                .toList();

        return result;
    }

    //
    public Long create(AccountRequest obj) {
        Account account = new Account();

        // PARSE DE ACCOUNT PARA ACCOUNT REQUEST
        BeanUtils.copyProperties(obj, account);

        // UTILIZADO SET DOS ENUMS
        account.setType(Type.valueOf(obj.getType()));
        account.setGroup(Group.valueOf(obj.getGroup()));
        account.setNature(Nature.valueOf(obj.getNature()));

        // ADICIONA EM ACCOUNT
        repository.save(account);
        return account.getId();
    }

    // DELETA CONTA
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ATUALIZA CONTA
    public void update(Long id, AccountRequest obj) {
        Account entity = fetchAccount(id);
        updateData(entity, obj);
        repository.save(entity);
    }

    // PROCURA UMA CONTA POR ID E LANCA EXCESSAO CASO A CONTA NAO EXISTA
    private Account fetchAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não existente"));
    }

    // METODO PARA ATUALIZAR UMA CONTA
    private void updateData(Account entity, AccountRequest request) {
        entity.setClassification(request.getClassification());
        entity.setCode(request.getCode());
        entity.setName(request.getName());

        Type type = Type.valueOf(request.getType());
        Nature nature = Nature.valueOf(request.getNature());
        Group group = Group.valueOf(request.getGroup());

        entity.setType(type);
        entity.setNature(nature);
        entity.setGroup(group);
    }

    // CONVERTE UM ACCOUNT EM ACCOUNT RESPONSE
    private AccountResponse convertToResponse(Account source) {
        AccountResponse response = new AccountResponse();
        BeanUtils.copyProperties(source, response);
        response.setType(source.getType().getLabel());
        response.setGroup(source.getGroup().getLabel());
        response.setNature(source.getNature().getLabel());
        return response;
    }

}
