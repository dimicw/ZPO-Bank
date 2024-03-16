package dimi.zpo.bank3.repositories;

import dimi.zpo.bank3.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
    //TransferEntity findByUsername(String username);
    List<TransferEntity> findByFromAccountOrToAccount(String fromAccount, String toAccount);
}
