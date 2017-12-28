package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("*")
    public String defaultMapping() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        return "redirect:/" + account.getId();
    }

    @GetMapping("/{id}")
    public String loadIndex(Model model, @PathVariable Long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            return "redirect:/";
        }

        model.addAttribute("account", account);
        model.addAttribute("cards", creditCardRepository.findByAccountName(account.getName()));
        return "index";
    }

    @PostMapping(value = "/addCard/{id}")
    public String submitCard(Model model, @RequestParam String number, @PathVariable Long id) {
        if (number.trim().isEmpty() || creditCardRepository.findByNumber(number) != null) {
            return "redirect:/{id}";
        }

        Account account = accountRepository.findById(id);
        creditCardRepository.save(new CreditCard(number, account));
        return "redirect:/{id}";
    }

}
