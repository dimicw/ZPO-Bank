package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.TransferEntity;
import dimi.zpo.bank3.repositories.TransferRepository;
import dimi.zpo.bank3.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public RedirectView redirectToHome() {
        return new RedirectView("/history/sort_by=date-desc=true");
    }

    @GetMapping("/history/sort_by={field}-desc={direction}")
    public String historySorted (@PathVariable String field,
                                 @PathVariable Boolean direction,
                                 Model model) {
        String accountNumber = "1234";

        List<TransferEntity> transfers = transferRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
        transfers = historyService.sortByField(transfers, field, direction);

        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("transfers", transfers);

        return "history";
    }

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private HistoryService historyService;
}
