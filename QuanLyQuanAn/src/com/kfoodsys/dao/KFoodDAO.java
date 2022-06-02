/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kfoodsys.dao;

import java.util.List;

/**
 *
 * @author phuho
 */
abstract public class KFoodDAO<Entity, Key> {
    abstract public void insert(Entity entity);

    abstract public void update(Entity entity);

    abstract public void delete(Key id);

    abstract public Entity selectById(Key id);

    abstract public List<Entity> selectAll();

    abstract protected List<Entity> selectBySql(String sql, Object... args);
}
