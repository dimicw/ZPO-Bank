package dimi.zpo.bank3.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotImplementedController {
    @GetMapping("/not-implemented")
    public String home(Authentication authentication) {

        return authentication.getPrincipal().toString();
        //return "We're sorry, this feature is not implemented yet.";
    }
}
