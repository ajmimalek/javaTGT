/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.esprit.pidev.tgt.enumeration.TypeContrat;
import java.util.Objects;

/**
 *
 * @author goldzeo
 */
public class Contrat {
private int id;
private Casting id_casting;
private Candidat idcandidat;
private float salaire;
private TypeContrat typeContrat;
private int dureeContrat;

    public Contrat(int id, Casting id_casting, Candidat idcandidat, float salaire, TypeContrat typeContrat, int dureeContrat) {
        this.id = id;
        this.id_casting = id_casting;
        this.idcandidat = idcandidat;
        this.salaire = salaire;
        this.typeContrat = typeContrat;
        this.dureeContrat = dureeContrat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Casting getId_casting() {
        return id_casting;
    }

    public void setId_casting(Casting id_casting) {
        this.id_casting = id_casting;
    }

    public Candidat getIdcandidat() {
        return idcandidat;
    }

    public void setIdcandidat(Candidat idcandidat) {
        this.idcandidat = idcandidat;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public int getDureeContrat() {
        return dureeContrat;
    }

    public void setDureeContrat(int dureeContrat) {
        this.dureeContrat = dureeContrat;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", id_casting=" + id_casting + ", idcandidat=" + idcandidat + ", salaire=" + salaire + ", typeContrat=" + typeContrat + ", dureeContrat=" + dureeContrat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.id_casting);
        hash = 73 * hash + Objects.hashCode(this.idcandidat);
        hash = 73 * hash + Float.floatToIntBits(this.salaire);
        hash = 73 * hash + Objects.hashCode(this.typeContrat);
        hash = 73 * hash + this.dureeContrat;
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
        final Contrat other = (Contrat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.salaire) != Float.floatToIntBits(other.salaire)) {
            return false;
        }
        if (this.dureeContrat != other.dureeContrat) {
            return false;
        }
        if (!Objects.equals(this.id_casting, other.id_casting)) {
            return false;
        }
        if (!Objects.equals(this.idcandidat, other.idcandidat)) {
            return false;
        }
        if (this.typeContrat != other.typeContrat) {
            return false;
        }
        return true;
    }


}
