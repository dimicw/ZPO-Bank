package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.AccountEntity;
import dimi.zpo.bank3.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            List<AccountEntity> accounts = accountRepository.findByOwnerId(auth.getName());

            if (!accounts.isEmpty()) {
                for (AccountEntity account : accounts) {
                    if (account.getAccountType() == 1)
                        model.addAttribute("basicSavings", "Balance: " + account.getBalance() + " PLN");
                    else if (account.getAccountType() == 2)
                        model.addAttribute("businessAccount", "Balance: " + account.getBalance() + " PLN");
                    else if (account.getAccountType() == 3)
                        model.addAttribute("investmentAccount", "Balance: " + account.getBalance() + " PLN");
                    else if (account.getAccountType() == 4)
                        model.addAttribute("premiumChecking", "Balance: " + account.getBalance() + " PLN");
                }
            }
        }

        return "home";
    }


    @Autowired
    private AccountRepository accountRepository;
}
