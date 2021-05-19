package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T>{

    /**
     * Creation du classe Generique
     */

    public T selectById(T obj)throws DALException;

    public List<T> selectAll()throws DALException;

    public  void update(T data)throws DALException;

    public void insert(T data)throws DALException;

    public void delete(T obj)throws DALException;

}
