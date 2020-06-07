/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidev.tgt.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Seif Henchir
 * @param <T>
 */
public interface IServices<T> {
     public boolean update(T t) throws SQLException;
     public List<T> findAll()throws SQLException;
     public T findById(int id) throws SQLException;
     public void delete(int id) throws SQLException;
     public void deleteAll() throws SQLException;
}
