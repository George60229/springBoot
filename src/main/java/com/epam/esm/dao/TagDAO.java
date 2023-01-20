package com.epam.esm.dao;

import com.epam.esm.model.Tag;

import java.util.List;
import java.util.Optional;


public interface TagDAO extends EntityDAO<Tag> {
    /**
     * create entity
     *
     * @param tag just entity
     * @return result this operation.
     */

    Tag create(Tag tag);

    /**
     * create entity
     *
     * @return result all tags from DB
     */
    List<Tag> getAllTags();

    /**
     * get entity id
     *
     * @param id just id
     * @return result all tags from DB
     */
    Optional<Tag> getById(int id);

    /**
     * get entity by name
     *
     * @param name just name
     * @return result all tags from DB
     */
    List<Tag> getByName(String name);


}
