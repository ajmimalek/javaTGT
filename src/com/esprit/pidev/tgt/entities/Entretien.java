/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.esprit.pidev.tgt.enumeration.StatutEnt;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author goldzeo
 */
public class Entretien {
     private int id;
    private LocalDate dateEnt;
    private StatutEnt statutEnt;
    private float noteEnt;

    public Entretien(int id, LocalDate dateEnt, StatutEnt statutEnt, float noteEnt) {
        this.id = id;
        this.dateEnt = dateEnt;
        this.statutEnt = statutEnt;
        this.noteEnt = noteEnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateEnt() {
        return dateEnt;
    }

    public void setDateEnt(LocalDate dateEnt) {
        this.dateEnt = dateEnt;
    }

    public StatutEnt getStatutEnt() {
        return statutEnt;
    }

    public void setStatutEnt(StatutEnt statutEnt) {
        this.statutEnt = statutEnt;
    }

    public float getNoteEnt() {
        return noteEnt;
    }

    public void setNoteEnt(float noteEnt) {
        this.noteEnt = noteEnt;
    }

    @Override
    public String toString() {
        return "Entretien{" + "id=" + id + ", dateEnt=" + dateEnt + ", statutEnt=" + statutEnt + ", noteEnt=" + noteEnt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.dateEnt);
        hash = 67 * hash + Objects.hashCode(this.statutEnt);
        hash = 67 * hash + Float.floatToIntBits(this.noteEnt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entretien other = (Entretien) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.noteEnt) != Float.floatToIntBits(other.noteEnt)) {
            return false;
        }
        if (!Objects.equals(this.dateEnt, other.dateEnt)) {
            return false;
        }
        if (this.statutEnt != other.statutEnt) {
            return false;
        }
        return true;
    }
    
    
    
}
