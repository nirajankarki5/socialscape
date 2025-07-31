package com.nirajan.socialscape.socialscape.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name="user_details")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    // map JSON column (in MySQL) to a String field.
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "interests", columnDefinition = "json")
    private List<String> interests;   // interests will contain the raw JSON string


    // ALL because delete user_details when user is deleted
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // constructors
    public UserDetail() {}

    public UserDetail(List<String> interests, User user) {
        this.interests = interests;
        this.user = user;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // toString()

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", interests=" + interests +
                ", user=" + user +
                '}';
    }
}
