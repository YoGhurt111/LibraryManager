package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BookEntity;
import com.shu.entity.BooktypeEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
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

    public static List<BookEntity> selectByType(String typeName){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BooktypeEntity> booktypeList = BookTypeDao.selectByName(typeName);
        List<BookEntity>bookList = new ArrayList<BookEntity>();
        bookList = session.createQuery("select b from BookEntity as b where typeid = ?").setParameter(0, booktypeList.get(0).getId()).list();
        transaction.commit();
        return bookList;
    }

    public static List<BookEntity> selectByAuthor(String author){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BookEntity>bookList = new ArrayList<BookEntity>();
        bookList = session.createQuery("select b from BookEntity as b where author = ?").setParameter(0, author).list();
        transaction.commit();
        return bookList;
    }

    public static List<BookEntity> selectByName(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BookEntity>bookList = new ArrayList<BookEntity>();
        bookList = session.createQuery("select b from BookEntity as b where bookname = ?").setParameter(0, name).list();
        return bookList;
    }

    public static List<BookEntity> selectByPublish(String publish){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BookEntity>bookList = new ArrayList<BookEntity>();
        bookList = session.createQuery("select b from BookEntity as b where publish = ?").setParameter(0, publish).list();
        return bookList;
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

    public static int update(String id, String typeid,
                             String name, String author,
                             String publish, Date publishDate,
                             int printTime, double price){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            int i = session.createQuery("UPDATE  BookEntity SET isbn=?,typeid=?,bookname=?,author=?,publish=?,publishdate=?,printtime=?,unitprice=? WHERE isbn=?")
                    .setParameter(0, id)
                    .setParameter(1, typeid)
                    .setParameter(2, name)
                    .setParameter(3, author)
                    .setParameter(4, publish)
                    .setParameter(5, publishDate)
                    .setParameter(6, printTime)
                    .setParameter(7, price)
                    .setParameter(8, id)
                    .executeUpdate();
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }


}
