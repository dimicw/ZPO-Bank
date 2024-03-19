package dimi.zpo.bank3.controllers;

import dimi.zpo.bank3.entities.UserEntity;
import dimi.zpo.bank3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String address,
                               @RequestParam String postalCode,
                               @RequestParam String email,
                               @RequestParam String phone) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        user.setPostalCode(postalCode);
        user.setEmail(email);
        user.setPhone(phone);

        userRepository.save(user);

        return "redirect:/login";
    }

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;
}

