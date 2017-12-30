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

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MockMvc mockMvc;
    private Account testAccount;

    @Before
    public void setUp() {
        testAccount = accountRepository.findOne(1L);
    }

    @Test
    public void testLoadIndexWithId() throws Throwable {
        mockMvc.perform(
                get("/").with(user(testAccount.getUsername())))
                .andExpect(redirectedUrl("/1")).andReturn().getRequest();
    }

    @Test
    public void testSubmitNewName() throws Throwable {
        mockMvc.perform(
                post("/changeName")
                        .with(user(testAccount.getUsername()))
                        .param("newName", "test"));

        assertEquals(1L, accountRepository.findAll().stream().filter(a -> a.getName().equals("test")).count());
    }

}
