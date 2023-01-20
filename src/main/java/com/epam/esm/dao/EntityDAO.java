package com.epam.esm.dao;


public interface EntityDAO<E> {

    /**
     * delete entity by id
     *
     * @param id just id
     * @return result this operation.
     */

    boolean deleteById(Integer id);


}
