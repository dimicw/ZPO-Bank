package dimi.zpo.bank3.repositories;

import dimi.zpo.bank3.entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {
    List<AccountTypeEntity> findById(int Id);
    List<AccountTypeEntity> findByName(String name);

    @Query("SELECT a FROM AccountTypeEntity a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', ?1,'%'))")
    public List<AccountTypeEntity> findByNameFree(String name);
}
