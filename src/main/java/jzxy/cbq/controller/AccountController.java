package jzxy.cbq.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.rautils.patch.ObjectPatcher;
import jzxy.cbq.entity.Account;
import jzxy.cbq.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/admin-ui/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    private final ObjectPatcher objectPatcher;

    @GetMapping
    public Page<Account> getList(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Account getOne(@PathVariable Integer id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<Account> getMany(@RequestParam List<Integer> ids) {
        return accountRepository.findAllById(ids);
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PatchMapping("/{id}")
    public Account patch(@PathVariable Integer id, @RequestBody JsonNode patchNode) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        account = objectPatcher.patchAndValidate(account, patchNode);

        return accountRepository.save(account);
    }

    @PatchMapping
    public List<Integer> patchMany(@RequestParam List<Integer> ids, @RequestBody JsonNode patchNode) {
        List<Account> accounts = new ArrayList<>(accountRepository.findAllById(ids));

        accounts.replaceAll(account -> objectPatcher.patchAndValidate(account, patchNode));

        List<Account> resultAccounts = accountRepository.saveAll(accounts);
        List<Integer> ids1 = resultAccounts.stream()
                .map(Account::getId)
                .toList();
        return ids1;
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            accountRepository.delete(account);
        }
        return account;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Integer> ids) {
        accountRepository.deleteAllById(ids);
    }
}
