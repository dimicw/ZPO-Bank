package dimi.zpo.bank3.services;

import dimi.zpo.bank3.entities.AccountEntity;
import dimi.zpo.bank3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NewAccountService {
    public String generateAccountNumber() {
        StringBuilder id = new StringBuilder();
        for(int  i = 0; i != 26; i++) {
            id.append(ThreadLocalRandom.current().nextInt(0, 10));
        }

        List<AccountEntity> accounts = accountRepository.findByNumber(id.toString());

        if (accounts.isEmpty())
            return id.toString();
        else
            return generateAccountNumber();
    }

    @Autowired
    private AccountRepository accountRepository;
}
