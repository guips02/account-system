package br.com.goomie.controller.account;

import br.com.goomie.model.account.Account;
import br.com.goomie.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Account account = service.findById(id);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(Account request) {
        service.create(request);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri()
                .buildAndExpand("/{id}", request.getId())
                .toUri();

        System.out.println("URI: " + uri);

        return ResponseEntity.created(uri).build();
    }
}
