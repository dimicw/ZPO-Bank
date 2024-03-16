package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.entities.UserEntity;
import dimi.zpo.bank3.repositories.TransferRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransferController {

    private final TransferRepository transferRepository;

    public TransferController(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        List<String> accounts = new ArrayList<>();

        accounts.add("1234");
        accounts.add("6852");

        model.addAttribute("accountOptions", accounts);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String registerTransfer(@RequestParam String fromAccount,
                                   @RequestParam String toAccount,
                                   @RequestParam BigDecimal amount) {
        TransferEntity transfer = new TransferEntity();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);

        transferRepository.save(transfer);

        return "redirect:/transfer/success";
    }
}
