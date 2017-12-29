package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/addCard")
    public String submitCard(Model model, @RequestParam String number) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        if (!number.trim().isEmpty() && creditCardRepository.findByNumber(number) == null) {
            creditCardRepository.save(new CreditCard(number, account));
        }

        return "redirect:/";
    }

}
