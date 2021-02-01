
package com.mycompany.bean;

import java.sql.Date;
import java.util.List;

/**
 * this is the model of the User entity in the database
 * @author azizg
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Date birthDate;
    private Country birthplace;
    private Country nationality;
    private List<UserSkill> userSkill;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    
    
    public User(int id, String name, String surname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
    public User( String name, String surname, String email, String phone) {
        
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public User(int id, String name, String surname, String email, String phone, Date birthDate, Country birthplace, Country nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.birthplace = birthplace;
        this.nationality = nationality;
    }
    
    
    
   
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(Country birthplace) {
        this.birthplace = birthplace;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public List<UserSkill> getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(List<UserSkill> userSkill) {
        this.userSkill = userSkill;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", birthDate=" + birthDate + ", birthplace=" + birthplace + ", nationality=" + nationality + '}';
    }
    
}
