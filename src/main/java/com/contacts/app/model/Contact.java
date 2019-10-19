package com.contacts.app.model;

import javax.persistence.*;

/**
 * Class to manage the contact data
 */
@Entity
@Table(name = "contact")
 public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idContact;
    private String name;
    private int age;
    private String phoneNumber;
    private String nickName;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Contact() {
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
