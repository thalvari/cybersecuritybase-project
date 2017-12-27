package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.CreditCard;
import sec.project.repository.AccountRepository;
import sec.project.repository.CreditCardRepository;

@Controller
public class CreditCardController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loadIndex(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = accountRepository.findByUsername(username).getName();
        model.addAttribute("cards", creditCardRepository.findByAccountName(name));
        return "index";
    }

    @RequestMapping(value = "/addCard", method = RequestMethod.POST)
    public String submitCard(@RequestParam String number) {
        if (number.trim().isEmpty()) {
            return "redirect:/index";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        creditCardRepository.save(new CreditCard(number, account));
        return "redirect:/index";
    }

}
