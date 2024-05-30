package com.example.demo.application.classBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Evenement {
    
    private long id;
    // The id of the user that has the event
    private long userId;
    // 0 for DC, 1 for Actor, 2 for Agent
    private int userType;
    private long yearAtt;
    private long monthAtt;
    private long dayAtt;
    private long hourAtt;
    private String libelle;
    private String description;

    public Evenement(long userId, int userType, long yearAtt, long monthAtt, long dayAtt, long hourAtt, String libelle, String description) {
        super();
        this.userId = userId;
        this.userType = userType;
        this.yearAtt = yearAtt;
        this.monthAtt = monthAtt;
        this.dayAtt = dayAtt;
        this.hourAtt = hourAtt;
        this.libelle = libelle;
        this.description = description;
    }

    public Evenement() {}

    @Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getUserType () {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public long getYearAtt() {
        return this.yearAtt;
    }

    public void setYearAtt(long yearAtt) {
        this.yearAtt = yearAtt;
    }
    
    public long getMonthAtt() {
        return this.monthAtt;
    }

    public void setMonthAtt(long monthAtt) {
        this.monthAtt = monthAtt;
    }

    public long getDayAtt() {
        return this.dayAtt;
    }

    public void setDayAtt(long dayAtt) {
        this.dayAtt = dayAtt;
    }

    public long getHourAtt() {
        return this.hourAtt;
    }

    public void setHourAtt(long hourAtt) {
        this.hourAtt = hourAtt;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
