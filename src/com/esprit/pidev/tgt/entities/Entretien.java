/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.esprit.pidev.tgt.enumeration.StatutEnt;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author goldzeo
 */
public class Entretien {
    private int id;
    private LocalDateTime dateEnt;
    private StatutEnt statutEnt;
    private float noteEnt;
    private Candidat candidat;

    public Entretien(int id, LocalDateTime dateEnt, StatutEnt statutEnt, float noteEnt) {
        this.id = id;
        this.dateEnt = dateEnt;
        this.statutEnt = statutEnt;
        this.noteEnt = noteEnt;
    }

    public Entretien(int id, LocalDateTime dateEnt, StatutEnt statutEnt, float noteEnt, Candidat candidat) {
        this.id = id;
        this.dateEnt = dateEnt;
        this.statutEnt = statutEnt;
        this.noteEnt = noteEnt;
        this.candidat = candidat;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateEnt() {
        return dateEnt;
    }

    public void setDateEnt(LocalDateTime dateEnt) {
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

  
    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    @Override
    public String toString() {
        return "Entretien{" + "id=" + id + ", dateEnt=" + dateEnt + ", statutEnt=" + statutEnt + ", noteEnt=" + noteEnt + ", candidat=" + candidat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.dateEnt);
        hash = 47 * hash + Objects.hashCode(this.statutEnt);
        hash = 47 * hash + Float.floatToIntBits(this.noteEnt);
        hash = 47 * hash + Objects.hashCode(this.candidat);
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
        if (!Objects.equals(this.candidat, other.candidat)) {
            return false;
        }
        return true;
    }
    

    
    
}
