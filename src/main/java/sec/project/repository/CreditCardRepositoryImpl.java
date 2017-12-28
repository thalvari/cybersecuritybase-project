package sec.project.repository;

import org.springframework.stereotype.Repository;
import sec.project.domain.CreditCard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CreditCard> findByAccountName(String name) {
        String sql = "SELECT card FROM CreditCard card WHERE card.account.name = '" + name + "')";
        Query query = entityManager.createQuery(sql, CreditCard.class);
        return query.getResultList();
    }

}
