/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.entities;

import com.esprit.pidev.tgt.enumeration.Role;
import java.util.Objects;

/**
 *
 * @author Seif Henchir
 */
public class Compte {
    private int id;
    private String username;
    private String password;
    private Role roleType;

    public Compte(int id, String username, String password, Role roleType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleType = roleType;
    }

    public Compte(String username, String password, Role roleType) {
        this.username = username;
        this.password = password;
        this.roleType = roleType;
    }

    public Compte(int id, String password) {
        this.id = id;
        this.password = password;
    }
    
    
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoleType() {
        return roleType;
    }

    public void setRoleType(Role roleType) {
        this.roleType = roleType;
    }
    

   

    @Override
    public String toString() {
        return "Compte{" + "id=" + id + ", username=" + username + ", password=" + password + ", roleType=" + roleType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.username);
        hash = 47 * hash + Objects.hashCode(this.password);
        hash = 47 * hash + Objects.hashCode(this.roleType);
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
        final Compte other = (Compte) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.roleType != other.roleType) {
            return false;
        }
        return true;
    }
    
    
    
}
