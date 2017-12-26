package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "redirect:/cards";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String loadCards(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        System.out.println(account.getUsername());
        model.addAttribute("account", account);
        model.addAttribute("cards", creditCardRepository.findByAccount(account));
        return "cards";
    }

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public String submitCard(@RequestParam String number) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        System.out.println(account.getUsername());
        creditCardRepository.save(new CreditCard(number, account));
        return "cards";
    }

}
