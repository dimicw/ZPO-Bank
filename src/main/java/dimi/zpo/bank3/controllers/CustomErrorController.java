package dimi.zpo.bank3.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error",
                        "Error 404 - Not Found");
                model.addAttribute("description",
                        "We can't seem to find the page you're looking for. " +
                                "It might have been moved or deleted, or perhaps you typed the URL incorrectly. " +
                                "Please check the URL or go back to the homepage.");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error",
                        "Error 500 - Internal Server Error");
                model.addAttribute("description",
                        "We're sorry, but something went wrong on our end. Our team has been notified " +
                                "and we'll fix it as soon as we can. Please try again later or go back to the homepage.");
            }
        }
        return "error";
    }
}
