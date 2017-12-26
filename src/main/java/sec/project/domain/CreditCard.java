package sec.project.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CreditCard extends AbstractPersistable<Long> {

    private String number;
    @ManyToOne
    private Account account;

    public CreditCard() {
        super();
    }

    public CreditCard(String number, Account account) {
        this();
        this.number = number;
        this.account = account;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
