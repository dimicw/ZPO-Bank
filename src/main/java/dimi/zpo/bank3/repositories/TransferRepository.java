package dimi.zpo.bank3.repositories;

import dimi.zpo.bank3.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {
    List<TransferEntity> findByFromAccountOrToAccount(String fromAccount, String toAccount);
}
