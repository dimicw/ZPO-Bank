package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.classes.HistoryEntry;
import dimi.zpo.bank3.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public RedirectView redirectToHome() {
        return new RedirectView("/history/sort_by=date-desc=true?page=0");
    }

    /*@GetMapping("/history/sort_by={field}-desc={direction}")
    public String historySorted (@PathVariable String field,
                                 @PathVariable Boolean direction,
                                 Model model) {

        List<HistoryEntry> entries = historyService.generateEntries(field, direction);
        model.addAttribute("entries", entries);

        return "history";
    }*/

    @GetMapping("/history/sort_by={field}-desc={direction}")
    public String historySorted (@PathVariable String field,
                                 @PathVariable Boolean direction,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 Model model) {

        Sort sort = direction ? Sort.by(field).descending() : Sort.by(field).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<HistoryEntry> entriesPage = historyService.generateEntries(field, direction, pageRequest);

        model.addAttribute("entries", entriesPage.getContent());
        model.addAttribute("totalPages", entriesPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "history";
    }

    @Autowired
    private HistoryService historyService;
}
