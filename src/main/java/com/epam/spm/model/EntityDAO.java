package com.epam.spm.model;

import javax.sql.DataSource;
import java.util.List;

public interface EntityDAO<E> {
    //todo fix interface
    //todo rename to dao
    //todo java doc in interface
    void setDataSource(DataSource ds);

    E getEntityByName(String name);

    List<E> listItems();

    boolean deleteById(Integer id);

    boolean deleteByName(String name);
}
