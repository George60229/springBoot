package com.epam.spm;

import javax.sql.DataSource;
import java.util.List;

public interface EntityDAO<E> {

    public void setDataSource(DataSource ds);

    public E getEntityByName(String name);


    public List<E> listItems();

    public void deleteById(Integer id);
}
