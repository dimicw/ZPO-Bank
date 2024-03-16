package dimi.zpo.bank3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmationController {
    @GetMapping("/transfer/success")
    public String basicSavings(Model model) {
        String title = "Success";
        String description = "Your bank transfer has been processed successfully.\n" +
                "The funds have been debited from your account and will be credited to the recipient’s account shortly.";

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "confirmation";
    }
}
