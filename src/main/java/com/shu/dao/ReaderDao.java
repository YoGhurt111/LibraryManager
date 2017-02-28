package com.shu.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.ReaderEntity;
import com.shu.entity.UsersEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
