package sec.project.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;
import sec.project.repository.CreditCardRepository;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private MockMvc mockMvc;
    private Account testAccount;

    @Before
    public void setUp() {
        testAccount = accountRepository.findOne(1L);
    }

    @Test
    public void testSubmitCard() throws Throwable {
        mockMvc.perform(
                post("/addCard")
                        .with(user(testAccount.getUsername()))
                        .param("number", "test"));

        assertEquals(1L, creditCardRepository.findAll().stream().filter(c -> c.getNumber().equals("test")).count());
    }

}
