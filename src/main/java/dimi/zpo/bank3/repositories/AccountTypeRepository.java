package dimi.zpo.bank3.repositories;

import dimi.zpo.bank3.entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {
    List<AccountTypeEntity> findById(int Id);
}
