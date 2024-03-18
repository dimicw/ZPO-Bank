package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.HistoryEntry;
import dimi.zpo.bank3.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        //String accountNumber = "1234";

        //model.addAttribute("accountNumber", accountNumber);
        //model.addAttribute("transfers", transfers);

        List<HistoryEntry> entries = historyService.generateEntries(field, direction);
        model.addAttribute("entries", entries);

        return "history";
    }

    @Autowired
    private HistoryService historyService;
}
