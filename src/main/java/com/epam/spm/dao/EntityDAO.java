package com.epam.spm.dao;


import com.epam.spm.dto.TagDTO;

import java.util.List;

public interface EntityDAO<E> {

    /**
     * get entity by name
     *
     * @param name just name
     * @return list of certificates.
     */
    List<E> getEntityByName(String name);
    /**
     * get all entity
     * @return list of certificates.
     */
    List<E> listItems();

    /**
     * delete entity by id
     * @param id just id
     * @return result this operation.
     */

    boolean deleteById(Integer id);

    /**
     * delete entity by name
     * @param name just name
     * @return result this operation
     */

    boolean deleteByName(String name);
}
