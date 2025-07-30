package com.vitae_api.models;

import jakarta.persistence.*;

import java.sql.Blob;
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

    @Lob
    @Column(name = "file", columnDefinition = "BYTEA")
    private byte[] file;



    private String geminiResponse;


    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Cv(User user, double grade, String geminiResponse) {
        this.user = user;
        this.grade = grade;
        this.geminiResponse = geminiResponse;
    }

    public Cv() {
    }

    public String getGeminiResponse() {
        return geminiResponse;
    }

    public void setGeminiResponse(String geminiResponse) {
        this.geminiResponse = geminiResponse;
    }

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

}
