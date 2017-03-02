package com.shu.view;

import com.shu.dao.UserDao;
import com.shu.entity.UsersEntity;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/*登录窗体*/
public class Login extends JFrame{
    private static final long serialVersionUID = 434385947601135857L;
    private JPanel textJP,loginJP,buttonJP;
    private JLabel textJL,usernameJL,passwordJL;
    private JButton confirmJB,resetJB;
    private JTextField usernameJTF;
    private JPasswordField pwdJPF;
    private static UsersEntity user;
    Font f1=new Font("黑体", Font.BOLD, 32);
    //此内部类需要放到下面的构造方法之上，否则，对象usernameJTF不识别
    class LoginAction implements ActionListener {
        //如果用户名和密码都不为空
        public void actionPerformed(final ActionEvent e) {//登录按钮的时间监听器类
            if (!"".equals(usernameJTF.getText()) && !"".equals(new String(pwdJPF.getPassword()))) {
                if (UserDao.check(usernameJTF.getText(), new String(pwdJPF.getPassword()))) {//从数据库中返回的用户名不为空（存在该用户）
                    try {
                        MainView frame = new MainView();//登录成功，运行主界面
                        user = UserDao.selectByName(usernameJTF.getText());
                        frame.setVisible(true);
                        Login.this.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "输入的用户名或密码错误，不能登录！");
                    usernameJTF.setText("");//清空
                    pwdJPF.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入用户名和密码！");
            }
        }
    }
    class ResetAction implements ActionListener {//重置按钮的时间监听器类
        public void actionPerformed(final ActionEvent e) {
            usernameJTF.setText("");
            pwdJPF.setText("");

        }
    }
    public Login(){
//		super();
        setSize(260,180); //设置窗口大小
        setTitle("图书管理系统登录界面");
        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
        int height=Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width/2-200, height/2-150);//为了获取屏幕的中间位置

        textJP=new JPanel();//为该窗体创建三个面板，分别放提示信息、登录信息和按钮区域
        loginJP=new JPanel();
        buttonJP=new JPanel();

        //提示信息面板
        textJL=new JLabel();
        textJL.setFont(f1);
        textJL.setText("图书管理系统");
        textJP.add(textJL);//加到提示信息面板
        this.add(textJP,BorderLayout.NORTH);

        //登录信息面板设计
        loginJP.setLayout(new GridLayout(2,2));
        usernameJL=new JLabel("用户名：");
        usernameJL.setHorizontalAlignment(SwingConstants.CENTER);
        usernameJTF=new JTextField();
        passwordJL=new JLabel("密   码：");
        passwordJL.setHorizontalAlignment(SwingConstants.CENTER);
        pwdJPF=new JPasswordField();
        loginJP.add(usernameJL);
        loginJP.add(usernameJTF);
        loginJP.add(passwordJL);
        loginJP.add(pwdJPF);//加到登录信息面板

        //按钮面板设计
        confirmJB=new JButton("登录");
        confirmJB.addActionListener(new LoginAction());//给事件源监听注册器
        resetJB=new JButton("重置");
        resetJB.addActionListener(new ResetAction());
        buttonJP.add(confirmJB);
        buttonJP.add(resetJB);
        this.add(loginJP,BorderLayout.CENTER);
        this.add(buttonJP,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序
        this.setVisible(true);
        setResizable(false);//取消最大化
    }
    public static void setUser(UsersEntity user) {
        Login.user = user;
    }
    public static UsersEntity getUser() {
        return user;
    }
    public static void main(String[] args) {
        Login lg=new Login();
    }
}