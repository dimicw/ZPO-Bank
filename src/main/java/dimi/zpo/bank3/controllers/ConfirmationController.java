package dimi.zpo.bank3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmationController {
    @GetMapping("/transfer/success")
    public String transferSuccess(Model model) {
        String title = "Success!";
        String description = "Your bank transfer has been processed successfully.\n" +
                "The funds have been debited from your account and will be credited to the recipient’s account shortly.";

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "confirmation";
    }

    @GetMapping("/transfer/fail")
    public String transferFail(Model model) {
        String title = "Something went wrong";
        String description = "Your bank transfer could not be processed.\n" +
                "The transaction has not been registered. Please, check if you have sufficient funds on your account and try again.";

        model.addAttribute("title", title);
        model.addAttribute("description", description);

        return "confirmation";
    }
}
