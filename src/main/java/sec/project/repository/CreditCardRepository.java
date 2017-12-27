package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>, CreditCardRepositoryCustom {

}
