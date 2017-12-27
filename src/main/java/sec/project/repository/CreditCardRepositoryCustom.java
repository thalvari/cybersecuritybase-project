package sec.project.repository;

import sec.project.domain.CreditCard;

import java.util.List;

public interface CreditCardRepositoryCustom {

    List<CreditCard> findByAccountName(String name);
}
