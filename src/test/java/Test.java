import com.shu.dao.UserDao;
import com.shu.entity.UsersEntity;
import com.shu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class Test {
    @org.junit.Test
    public void userTest(){
        UserDao userDao = new UserDao();
        System.out.println(userDao.selectById(10));
    }
}
