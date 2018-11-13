package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.CreditCard;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByNumber(String number);

    List<CreditCard> findByAccountName(String name);
}
