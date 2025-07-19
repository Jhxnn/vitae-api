package com.vitae_api.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cvs")
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID cvId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User user;

    private double grade;

    private String url;

    public UUID getCvId() {
        return cvId;
    }

    public void setCvId(UUID cvId) {
        this.cvId = cvId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
