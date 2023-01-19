package com.epam.spm.dao;

import com.epam.spm.model.Tag;

import java.util.List;


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
    Tag getById(int id);

    List<Tag> getByName(String name);


}
