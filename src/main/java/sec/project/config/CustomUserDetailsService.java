package sec.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.domain.CreditCard;
import sec.project.repository.AccountRepository;
import sec.project.repository.CreditCardRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        Account account = new Account("admin", encoder.encode("123456"), "Admin");
        accountRepository.save(account);
        creditCardRepository.save(new CreditCard("000000001", account));

        account = new Account("abel", encoder.encode("password"), "Abel");
        accountRepository.save(account);
        creditCardRepository.save(new CreditCard("000000002", account));
        creditCardRepository.save(new CreditCard("000000003", account));
        creditCardRepository.save(new CreditCard("000000004", account));

        account = new Account("bob", encoder.encode("12345678"), "Bob");
        accountRepository.save(account);
        creditCardRepository.save(new CreditCard("000000005", account));
        creditCardRepository.save(new CreditCard("000000006", account));

        account = new Account("charlie", encoder.encode("qwerty"), "Charlie");
        accountRepository.save(account);
        creditCardRepository.save(new CreditCard("000000007", account));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

}
