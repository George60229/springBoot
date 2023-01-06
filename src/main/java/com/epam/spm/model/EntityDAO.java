package com.epam.spm.model;

import javax.sql.DataSource;
import java.util.List;

public interface EntityDAO<E> {


    E getEntityByName(String name);

    List<E> listItems();

    boolean deleteById(Integer id);

    boolean deleteByName(String name);
}
