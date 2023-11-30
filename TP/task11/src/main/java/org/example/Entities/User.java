package org.example.Entities;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name  = "_user")
@Check(constraints = "gender IN ('М', 'Ж')")
public class User {

    public User(){};
    public User(String firstname, String lastname, String gender, String phone, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "user_id")
    private long user_id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", out_messages=" + out_messages +
                '}';
    }

    @OneToMany(mappedBy = "source_user", cascade = CascadeType.ALL)
    private List<Message> out_messages = new ArrayList<>();
    @OneToMany(mappedBy = "target_user", cascade = CascadeType.ALL)
    private List<Message> income_messages = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Message> getOut_messages() {
        return out_messages;
    }

    public List<Message> getIncome_messages() {
        return income_messages;
    }

    public long getUser_id() {
        return user_id;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
