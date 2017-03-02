package com.shu.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shu.entity.BooktypeEntity;
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
        List<UsersEntity> userList = session.createQuery("select u from UsersEntity as u ").list();
        transaction.commit();
        return userList;
    }

    public static UsersEntity selectByName(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UsersEntity user = (UsersEntity) session.createQuery("select u from UsersEntity as u where name=?")
                .setParameter(0, name).list().get(0);
        transaction.commit();
        return user;
    }

    public static boolean check(String userName, String password){
        List<UsersEntity> userList = selectAll();
        UsersEntity user = new UsersEntity();
        for(int i=0; i<userList.size(); i++){
            user = userList.get(i);
            if ((user.getName().equals(userName)) && (user.getPassword().equals(password))){
                return true;
            }
        }
        return false;
    }

    public static int insert(UsersEntity user){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
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
            int i = session.createQuery("delete from UsersEntity where id = ?")
                    .setParameter(0, id).executeUpdate();
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    public static int updateUserPWD(int id, String pwd){
        Gson gson = new Gson();
        UsersEntity user = gson.fromJson(selectById(id), UsersEntity.class);
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        user.setPassword(pwd);
        try {
            session.update(user);
            transaction.commit();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

}
