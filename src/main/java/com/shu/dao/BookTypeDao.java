package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BooktypeEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/2/28.
 */
public class BookTypeDao {
    private static Session session;

    public static String selectById(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BooktypeEntity bookType = session.get(BooktypeEntity.class, id);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        transaction.commit();
        return gson.toJson(bookType);
    }

    public static List<BooktypeEntity> selectByName(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BooktypeEntity> bookTypeList = session.createQuery("select bt from BooktypeEntity  as bt where typename=?").setParameter(0,name).list();
        transaction.commit();
        return bookTypeList;
    }

    public static List<BooktypeEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BooktypeEntity> bookTypeList = session.createQuery("select bt from BooktypeEntity  as bt ").list();
        transaction.commit();
        return bookTypeList;
    }


    public static int insert(BooktypeEntity bookType){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(bookType);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public static int update(int id, String typeName){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            int i = session.createQuery("UPDATE  BooktypeEntity SET id=?,typename=?  WHERE id=?")
                    .setParameter(0, id)
                    .setParameter(1, typeName)
                    .setParameter(2, id)
                    .executeUpdate();
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public static int deleteById(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            int i = session.createQuery("delete from BooktypeEntity where id = ?")
                    .setParameter(0, id).executeUpdate();
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
