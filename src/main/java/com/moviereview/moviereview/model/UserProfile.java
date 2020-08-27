package com.moviereview.moviereview.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@NamedQuery(name = "UserProfile.findAll", query = "SELECT d FROM UserProfile d")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public UserProfile() {
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", profileType=" + profileType +

                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getProfileStatus() {
        return profileType;
    }

    public void setProfileStatus(Type profileStatus) {
        this.profileType = profileStatus;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserProfile.Type profileType;

    public enum Type {
        NORMAL,
        ADMIN
    }

}
