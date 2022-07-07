package com.nvq.RegistrationAndLogin.pojo;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, length = 45)
    private String email;
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Column(name = "verificationCode", length = 64)
    private String verificationCode;
    @Column(name = "enable")
    private boolean enable;

    public String getVerificationCode() {
        return verificationCode;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
