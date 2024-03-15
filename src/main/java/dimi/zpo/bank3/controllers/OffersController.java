package dimi.zpo.bank3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OffersController {
    @GetMapping("/offers/basic-savings")
    public String basicSavings(Model model) {
        String title = "Basic Savings";
        String description = "Open a Basic Savings account with competitive interest rates.";
        String image = "basic_savings.png";

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", image);

        return "offer";
    }

    @GetMapping("/offers/premium-checking")
    public String premiumChecking(Model model) {
        String title = "Premium Checking";
        String description = "Get exclusive benefits with our Premium Checking account.";
        String image = "premium_checking.png";

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", image);

        return "offer";
    }

    @GetMapping("/offers/investment-accounts")
    public String investmentAccounts(Model model) {
        String title = "Investment Accounts";
        String description = "Explore investment options tailored to your financial goals.";
        String image = "investment_account.png";

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", image);

        return "offer";
    }

    @GetMapping("/offers/business-accounts")
    public String businessAccounts(Model model) {
        String title = "Business Accounts";
        String description = "Manage your business finances efficiently with our Business accounts.";
        String image = "business_accounts.png";

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("image", image);

        return "offer";
    }
}