package com.shu.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BorrowbookEntity;
import com.shu.entity.UsersEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class BorrowBookDao {
    private static Session session;


    public static int borrowBook(BorrowbookEntity borrowbookEntity){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(borrowbookEntity);
            transaction.commit();
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public static int returnBook(String readerid, String isbn, Date returnDate){
        Gson gson = new Gson();
        BorrowbookEntity borrowbookEntity = selectByReaderid(readerid).get(0);
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        borrowbookEntity.setReturndate(returnDate);
        try {
            session.update(borrowbookEntity);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public static List<BorrowbookEntity> selectByReaderid(String id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowbookEntity> bbList = session.createQuery("select bb from BorrowbookEntity as bb where readerid=?")
                .setParameter(0, id)
                .list();
        transaction.commit();
        return bbList;
    }
}
