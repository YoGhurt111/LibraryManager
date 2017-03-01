package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.ReaderEntity;
import com.shu.entity.UsersEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.Resource;
import javax.security.auth.login.Configuration;
import java.util.List;


public class UserDao {
    private static Session session;

    public static String selectById(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        UsersEntity user = session.get(UsersEntity.class, id);
        transaction.commit();
        return gson.toJson(user);
    }

    public static List<UsersEntity> selectAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List userList = session.createQuery("select u from UsersEntity as u ").list();
        transaction.commit();
        return userList;
    }

    public static boolean check(String userName, String password){
        List<UsersEntity> userList = selectAll();
        UsersEntity user = new UsersEntity();
        for(int i=0; i<userList.size(); i++){
            user = userList.get(i);
            if (user.getName() == userName && user.getPassword() == password){
                return true;
            }
        }
        return false;
    }
}
