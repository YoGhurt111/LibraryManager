package com.shu.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BookEntity;
import com.shu.entity.ReaderEntity;
import com.shu.entity.UsersEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReaderDao {
    private static Session session;

    public static String selectById(String id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ReaderEntity reader = session.get(ReaderEntity.class, id);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        transaction.commit();
        return gson.toJson(reader);
    }

    public static List<ReaderEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List readerList = session.createQuery("select r from ReaderEntity as r ").list();
        transaction.commit();
        return readerList;
    }

    public static List<ReaderEntity> selectByName(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ReaderEntity>readerList = new ArrayList<ReaderEntity>();
        readerList = session.createQuery("select r from ReaderEntity as r where name = ?").setParameter(0, name).list();
        transaction.commit();
        return readerList;
    }

    public static List<ReaderEntity> selectByDept(String dept){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ReaderEntity>readerList = new ArrayList<ReaderEntity>();
        readerList = session.createQuery("select r from ReaderEntity as r where dept = ?").setParameter(0, dept).list();
        transaction.commit();
        return readerList;
    }

    public static List<ReaderEntity> selectByType(int typeid){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ReaderEntity>readerList = new ArrayList<ReaderEntity>();
        readerList = session.createQuery("select r from ReaderEntity as r where type = ?").setParameter(0, typeid).list();
        transaction.commit();
        return readerList;
    }

    public static int update(String id, int typeid,
                             String name, int age,
                             String sex, String phoneNum,
                             String dept, Date regdate){
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            try {
                int i = session.createQuery("UPDATE  ReaderEntity SET readerid=?,type=?,name=?,age=?,sex=?,phone=?,dept=?,regdate=? WHERE readerid=?")
                        .setParameter(0, id)
                        .setParameter(1, typeid)
                        .setParameter(2, name)
                        .setParameter(3, age)
                        .setParameter(4, sex)
                        .setParameter(5, phoneNum)
                        .setParameter(6, dept)
                        .setParameter(7, regdate)
                        .setParameter(8, id)
                        .executeUpdate();
                transaction.commit();
                return 1;
            }
            catch (Exception e){
                return 0;
            }
    }

    public static int insert(ReaderEntity readerEntity){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(readerEntity);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
