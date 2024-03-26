package dimi.zpo.bank3.repositories;

import dimi.zpo.bank3.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByOwnerId(String ownerId);
    List<AccountEntity> findByNumber(String number);
}
