package com.shu.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.ReadertypeEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReaderTypeDao {
    private static Session session;

    public static String selectById(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ReadertypeEntity readerType = session.get(ReadertypeEntity.class, id);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        transaction.commit();
        return gson.toJson(readerType);
    }

    public static List<ReadertypeEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List readerTypeList = session.createQuery("select rt from ReadertypeEntity  as rt ").list();
        transaction.commit();
        return readerTypeList;
    }

    public static int insert(ReadertypeEntity readerType){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(readerType);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
