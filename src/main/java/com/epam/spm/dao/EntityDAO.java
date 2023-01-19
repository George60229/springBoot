package com.epam.spm.dao;


public interface EntityDAO<E> {

    /**
     * delete entity by id
     *
     * @param id just id
     * @return result this operation.
     */

    boolean deleteById(Integer id);

    /**
     * delete entity by name
     *
     * @param name just name
     * @return result this operation
     */


}
