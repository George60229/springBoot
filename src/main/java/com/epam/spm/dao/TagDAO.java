package com.epam.spm.dao;

import com.epam.spm.model.Tag;

import java.util.List;


public interface TagDAO extends EntityDAO<Tag> {

   Tag create(Tag tag);
   List<Tag> getAllTags();

   Tag getById(int id);



}
