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
import sec.project.repository.AccountRepository;
import sec.project.repository.CreditCardRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping("*")
    public String defaultMapping() {
//        return "redirect:/";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        return "redirect:/" + account.getId();
    }

//    @GetMapping("/")
//    public String loadIndex(Model model) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Account account = accountRepository.findByUsername(username);
//        model.addAttribute("account", account);
//        model.addAttribute("cards", creditCardRepository.findByAccountName(account.getName()));
//        return "index";
//    }

    @GetMapping("/{id}")
    public String loadIndexWithId(Model model, @PathVariable Long id) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            return "redirect:/";
        }

        model.addAttribute("account", account);
        model.addAttribute("cards", creditCardRepository.findByAccountName(account.getName()));
        return "index";
    }

    @PostMapping(value = "/changeName")
    public String submitNewName(Model model, @RequestParam String newName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        if (!newName.trim().isEmpty() && accountRepository.findByName(newName) == null) {
            account.setName(newName);
            accountRepository.save(account);
        }

        return "redirect:/";
    }

}
