package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.AccountEntity;
import dimi.zpo.bank3.entities.AccountTypeEntity;
import dimi.zpo.bank3.repositories.AccountRepository;
import dimi.zpo.bank3.repositories.AccountTypeRepository;
import dimi.zpo.bank3.services.NewAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class OffersController {
    @GetMapping("/offers/offer={accountType}/")
    public String basicSavings(@PathVariable String accountType,
                               Model model) {
        String title = null, description = null, image = null;

        if (accountType.equals("basic-savings")) {
            title = "Basic Savings";
            description = "Open a Basic Savings account with competitive interest rates.";
            image = "basic_savings.png";
        } else if (accountType.equals("premium-checking")) {
            title = "Premium Checking";
            description = "Get exclusive benefits with our Premium Checking account.";
            image = "premium_checking.png";
        } else if (accountType.equals("investment-account")) {
            title = "Investment Accounts";
            description = "Explore investment options tailored to your financial goals.";
            image = "investment_account.png";
        } else if (accountType.equals("business-account")) {
            title = "Business Accounts";
            description = "Manage your business finances efficiently with our Business accounts.";
            image = "business_accounts.png";
        } else
            title = accountType;

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", image);
        model.addAttribute("accountTypeUrl", accountType);

        return "offer";
    }

    @GetMapping("/offers/offer={accountType}/open-account")
    public String openAccount(@PathVariable String accountType,
                              Model model) {
        String type = null;

        if (accountType.equals("basic-savings"))
            type = "a Basic Savings Account";
        else if (accountType.equals("premium-checking"))
            type = "a Premium Checking Account";
        else if (accountType.equals("investment-account"))
            type = "an Investment Account";
        else if (accountType.equals("business-account"))
            type = "a Business Account";

        model.addAttribute("type", type);
        model.addAttribute("accountTypeUrl", accountType);

        return "confirmation";
    }

    @GetMapping("/offers/offer={accountType}/open")
    public String saveAccount(@PathVariable String accountType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountTypeEntity type = accountTypeRepository.findByName(
                accountType.replace('-', ' ')).get(0);

        AccountEntity account = new AccountEntity();

        account.setNumber(newAccountService.generateAccountNumber());
        account.setOwnerId(auth.getName());
        account.setBalance(new BigDecimal(0));
        account.setAccountType(type.getId());

        accountRepository.save(account);

        return "home";
    }
    /*@PostMapping("/offers/offer={accountType}/open-account")
    public String saveaaaccount(@PathVariable String accountType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if( accountType.replace('-', ' ').trim().equals("Basic-Savings".replace('-', ' ').trim())) System.out.println("yay");
        else System.out.println(accountType.replace('-', ' ').trim() + "\n" + "Basic-Savings".replace('-', ' ').trim());
        if (accountTypeRepository.findByName("basic-savings".replace('-', ' ')).isEmpty()) return "redirect:/not-implemented";
        AccountTypeEntity type = accountTypeRepository.findByName(
                accountType.replace('-', ' ')).get(0);
        System.out.println(type);

        AccountEntity account = new AccountEntity();

        account.setNumber(newAccountService.generateAccountNumber());
        account.setOwnerId(auth.getName());
        account.setBalance(new BigDecimal(0));
        account.setAccountType(type.getId());

        accountRepository.save(account);

        return "home";
    }*/


    @Autowired
    private NewAccountService newAccountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountTypeRepository accountTypeRepository;
}