package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.AccountEntity;
import dimi.zpo.bank3.entities.AccountTypeEntity;
import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.repositories.AccountRepository;
import dimi.zpo.bank3.repositories.AccountTypeRepository;
import dimi.zpo.bank3.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class TransferController {

    private final TransferRepository transferRepository;

    public TransferController(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<AccountEntity> accounts = accountRepository.findByOwnerId(auth.getName());
        List<AccountTypeEntity> accountTypes = accountTypeRepository.findAll();

        model.addAttribute("accountOptions", accounts);
        model.addAttribute("accountTypes", accountTypes);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String registerTransfer(@RequestParam String fromAccount,
                                   @RequestParam String toAccount,
                                   @RequestParam BigDecimal amount) {
        transferRepository.save(new TransferEntity(fromAccount, toAccount, amount));

        return "redirect:/transfer/success";
    }

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
}
