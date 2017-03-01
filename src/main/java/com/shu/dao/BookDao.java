package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BookEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Dell on 2017/2/28.
 */
public class BookDao {
    private static Session session;

    public static String selectById(String isbn){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        BookEntity book = session.get(BookEntity.class, isbn);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        transaction.commit();
        return gson.toJson(book);
    }

    public static List<BookEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List bookList = session.createQuery("select b from BookEntity  as b ").list();
        transaction.commit();
        return bookList;
    }


    public static int insert(BookEntity book){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(book);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
