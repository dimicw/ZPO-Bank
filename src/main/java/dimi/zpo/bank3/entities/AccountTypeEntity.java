package dimi.zpo.bank3.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "account_types")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
