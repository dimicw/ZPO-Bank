package dimi.zpo.bank3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class OffersController {
    @GetMapping("/offers/basic-savings")
    public String basicSavings() {

        return "Basic savings";
    }

    @GetMapping("/offers/premium-checking")
    public String premiumChecking() {

        return "premium checking";
    }

    @GetMapping("/offers/investment-accounts")
    public String investmentAccounts() {

        return "investment accounts";
    }

    @GetMapping("/offers/business-accounts")
    public String businessAccounts() {

        return "business accounts";
    }
}