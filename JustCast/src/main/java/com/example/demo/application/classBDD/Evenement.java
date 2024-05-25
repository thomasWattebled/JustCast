package com.example.demo.application.classBDD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Evenement {
    
    private long id;
    // The id of the user that has the event
    private long idUserEvenement;
    private long year;
    private long month;
    private long day;
    private long hour;
    private String libelle;
    private String description;

    public Evenement(long idUserEvenement, long year, long month, long day, long hour, String libelle, String description) {
        super();
        this.idUserEvenement = idUserEvenement;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.libelle = libelle;
        this.description = description;
    }

    @Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public long getIdUserEvenement() {
        return this.idUserEvenement;
    }

    public void setIdUserEvenement(long idUserEvenement) {
        this.idUserEvenement = idUserEvenement;
    }

    public long getYear() {
        return this.year;
    }

    public void setYear(long year) {
        this.year = year;
    }
    
    public long getMonth() {
        return this.month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getDay() {
        return this.day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public long getHour() {
        return this.hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
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
