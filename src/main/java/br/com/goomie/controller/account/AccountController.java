package br.com.goomie.controller.account;

import br.com.goomie.model.account.Account;
import br.com.goomie.model.account.AccountRequest;
import br.com.goomie.model.account.AccountResponse;
import br.com.goomie.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// DEFINE QUE A CLASSE É UM CONTROLLER
@RestController
// DEFINE O MAPEAMENTO DA REQUISICAO
@RequestMapping("/account")
// CONSTRUTORES QUE POSSUI (final)
@RequiredArgsConstructor
public class AccountController {

    // INSERCAO DA DEPENDENCIA DO  SERVICO
    private final AccountService service;

    // ACESSO DO METODO POR /ID NO URI
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        AccountResponse account = service.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AccountRequest request) {
        Long id = service.create(request);
        URI uri = createURI(id);
        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody AccountRequest request) {

        service.update(id, request);
        return ResponseEntity.noContent().build();

    }

    private URI createURI(Long accountId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(accountId)
                .toUri();
    }
}
