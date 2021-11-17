/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.dao;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public abstract class LibraryDAO<EntityType, KeyType> {

    public abstract void insert(EntityType entity);

    public abstract void update(EntityType entity);

    public abstract List<EntityType> selectAll();

    public abstract EntityType selectByID(KeyType id);

    public abstract List<EntityType> selectBySQL(String sql, Object... args);

}
