package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public String history(Model model) {

        String accountNumber = "1234";

        List<TransferEntity> transfers = transferRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
        for (TransferEntity transfer : transfers) {
        }

        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("transfers", transfers);

        return "history";
    }

    @Autowired
    private TransferRepository transferRepository;
}
