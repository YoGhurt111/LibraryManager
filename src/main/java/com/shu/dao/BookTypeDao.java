package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BooktypeEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static List<BooktypeEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List bookTypeList = session.createQuery("select bt from BooktypeEntity  as bt ").list();
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
}
