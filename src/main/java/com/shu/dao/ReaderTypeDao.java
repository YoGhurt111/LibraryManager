package com.shu.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.ReaderEntity;
import com.shu.entity.ReadertypeEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
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

    public static List<ReadertypeEntity> selectByTypeName(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ReadertypeEntity>readerTypeList = new ArrayList<ReadertypeEntity>();
        readerTypeList = session.createQuery("select rt from ReadertypeEntity  as rt where typename = ?").setParameter(0, name).list();
        transaction.commit();
        return readerTypeList;
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

    public static int update(int id, String typeName, int num, int limit){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            int i = session.createQuery("UPDATE  ReadertypeEntity SET id=?,typename=?,maxborrownum=?,limitday=? WHERE id=?")
                    .setParameter(0, id)
                    .setParameter(1, typeName)
                    .setParameter(2, num)
                    .setParameter(3, limit)
                    .setParameter(4, id)
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
            int i = session.createQuery("delete from ReadertypeEntity where id = ?")
                    .setParameter(0, id).executeUpdate();
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
