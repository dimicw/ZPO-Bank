package dimi.zpo.bank3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
    @GetMapping("/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //String username = authentication.getName();
        //model.addAttribute("username", username);

        return "home";
    }
}