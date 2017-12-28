package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(value = "/changeName/{id}")
    public String submitNewName(Model model, @RequestParam String newName, @PathVariable Long id) {
        if (newName.trim().isEmpty() || accountRepository.findByName(newName) != null) {
            return "redirect:/{id}";
        }

        Account account = accountRepository.findOne(id);
        account.setName(newName);
        model.addAttribute("id", id);
        accountRepository.save(account);
        return "redirect:/{id}";
    }

}
