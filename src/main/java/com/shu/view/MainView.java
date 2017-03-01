//package com.shu.view;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JToolBar;
//import javax.swing.SwingConstants;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.EmptyBorder;
//
//public class MainView extends JFrame{
//    private static final long serialVersionUID = 2316762795668645542L;
//    private JPanel ReaderManageJP,BookManageJP,BookBorrowJP,baseInfoJP,userManageJP;
//    private JLabel ReaderManageJL,BookManageJL,BookBorrowJL,baseInfoJL,userManageJL;
//    private JButton RMTJB,RMCXJB,BMTJB,BMCXJB,BBGJB,BBJJB,BIRCJB,BIBCJB,BIMJB,UMCJB,UMAJB,UMDJB;
//    class BookAddListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            BookAdd bookadd=new BookAdd();
//        }
//    }
//    class BookSelModListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            BookSM bookadd=new BookSM();
//        }
//    }
//
//    class ReaderAddListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            ReaderAdd readeradd=new ReaderAdd();
//        }
//    }
//
//    class ReaderSelUpdListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            ReaderSM rs=new ReaderSM();
//        }
//    }
//    class ReaderTypeListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            ReaderType rt=new ReaderType();
//        }
//    }
//    class BookTypeListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            BookType rt=new BookType();
//        }
//    }
//
//    class updatePasswordListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            Code pwd=new Code();
//        }
//    }
//
//    class userAddListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            AddUser user=new AddUser();
//        }
//    }
//    class userDeleteListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            DeleteUser user=new DeleteUser();
//        }
//    }
//
//    class BookBorrowListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            BookBorrow bookborrow=new BookBorrow();
//        }
//    }
//
//    class SetfineListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            Mone fine=new Money();
//        }
//    }
//    class BookReturnListener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {
//            BookReturn bookreturn=new BookReturn();
//        }
//    }
//    public MainView(){
//        setSize(600,500);
//        setTitle("图书借阅系统");
//        int width=Toolkit.getDefaultToolkit().getScreenSize().width;
//        int height=Toolkit.getDefaultToolkit().getScreenSize().height;
//        this.setLocation(width/2-200, height/2-150);//为了获取屏幕的中间位置
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序
//        this.setVisible(true);
//
//        ReaderManageJP=new JPanel();
//        BookManageJP=new JPanel();
//        BookBorrowJP=new JPanel();
//        baseInfoJP=new JPanel();
//        userManageJP=new JPanel();
//
//        GridLayout gridLayout = new GridLayout(5, 3);
//        gridLayout.setVgap(8);
//        gridLayout.setHgap(8);
//        this.setLayout(gridLayout);
//        //读者信息管理面板
//        ReaderManageJP=new JPanel();
//        ReaderManageJP.setLayout(new GridLayout(1,2));
//        ReaderManageJP.setBorder(new EmptyBorder(20,5,20,5));
//        ReaderManageJL=new JLabel("读者信息管理:");
//        ReaderManageJL.setHorizontalAlignment(SwingConstants.CENTER);
//        ReaderManageJP.add(ReaderManageJL);
//        RMTJB=new JButton("读者信息添加");
//        RMTJB.addActionListener(new ReaderAddListener());
//        RMCXJB=new JButton("读者信息查询与修改");
//        RMCXJB.addActionListener(new ReaderSelUpdListener());
//        ReaderManageJP.add(RMTJB);
//        ReaderManageJP.add(RMCXJB);
//        this.add(ReaderManageJP);
//        //图书信息管理面板
//        BookManageJP=new JPanel();
//        BookManageJP.setLayout(new GridLayout(1,2));
//        BookManageJP.setBorder(new EmptyBorder(20,5,20,5));
//        BookManageJL=new JLabel("图书信息管理:");
//        BookManageJL.setHorizontalAlignment(SwingConstants.CENTER);
//        BookManageJP.add(BookManageJL);
//        BMTJB=new JButton("图书信息添加");
//        BMTJB.addActionListener(new BookAddListener());
//        BMCXJB=new JButton("图书信息查询与修改");
//        BMCXJB.addActionListener(new BookSelModListener());
//        BookManageJP.add(BMTJB);
//        BookManageJP.add(BMCXJB);
//        this.add(BookManageJP);
//        //图书借阅管理面板
//        BookBorrowJP=new JPanel();
//        BookBorrowJP.setLayout(new GridLayout(1,2));
//        BookBorrowJP.setBorder(new EmptyBorder(20,5,20,5));
//        BookBorrowJL=new JLabel("图书借阅信息管理:");
//        BookBorrowJL.setHorizontalAlignment(SwingConstants.CENTER);
//        BookBorrowJP.add(BookBorrowJL);
//        BBGJB=new JButton("图书借阅");
//        BBGJB.addActionListener(new BookBorrowListener());
//        BBJJB=new JButton("图书归还");
//        BBJJB.addActionListener(new BookReturnListener());
//        BookBorrowJP.add(BBGJB);
//        BookBorrowJP.add(BBJJB);
//        this.add(BookBorrowJP);
//        //基础信息维护面板
//        baseInfoJP=new JPanel();
//        baseInfoJP.setLayout(new GridLayout(1,3));
//        baseInfoJP.setBorder(new EmptyBorder(20,20,20,20));
//        baseInfoJL=new JLabel("基础信息维护:");
//        baseInfoJL.setHorizontalAlignment(SwingConstants.CENTER);
//        baseInfoJP.add(baseInfoJL);
//        BIRCJB=new JButton("读者类别设置");
//        BIRCJB.addActionListener(new ReaderTypeListener());
//        BIBCJB=new JButton("图书类别设置");
//        BIBCJB.addActionListener(new BookTypeListener());
//        BIMJB=new JButton("罚金设置");
//        BIMJB.addActionListener(new SetfineListener());
//        baseInfoJP.add(BIRCJB);
//        baseInfoJP.add(BIBCJB);
//        baseInfoJP.add(BIMJB);
//        this.add(baseInfoJP);
//        //用户管理面板
//        userManageJP=new JPanel();
//        userManageJP.setLayout(new GridLayout(1,3));
//        userManageJP.setBorder(new EmptyBorder(20,20,20,20));
//        userManageJL=new JLabel("用户管理:");
//        userManageJL.setHorizontalAlignment(SwingConstants.CENTER);
//        userManageJP.add(userManageJL);
//        UMCJB=new JButton("修改密码");
//        UMCJB.addActionListener(new updatePasswordListener());
//        UMAJB=new JButton("用户添加");
//        UMAJB.addActionListener(new userAddListener());
//        UMDJB=new JButton("用户删除");
//        UMDJB.addActionListener(new userDeleteListener());
//        userManageJP.add(UMCJB);
//        userManageJP.add(UMAJB);
//        userManageJP.add(UMDJB);
//        this.add(userManageJP);
//    }
//
//    public static void main(String[] args) {
//        new MainView();
//    }
//}