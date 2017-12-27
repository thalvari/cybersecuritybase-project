package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/changeName", method = RequestMethod.POST)
    public String submitNewName(@RequestParam String newName) {
        if (newName.trim().isEmpty()) {
            return "redirect:/index";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findByUsername(username);
        account.setName(newName);
        accountRepository.save(account);
        return "redirect:/index";
    }

}
