package dimi.zpo.bank3.classes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private String name;
    private String surname;
    private String address;
    private String postalCode;
    private String email;
    private String phone;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      String name, String surname, String address, String postalCode, String email, String phone) {
        super(username, password, authorities);
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
