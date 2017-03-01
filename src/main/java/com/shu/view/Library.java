//package com.shu.view;
//
//import java.awt.BorderLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JToolBar;
//import javax.swing.border.BevelBorder;
//
//
//
//
//
///* 图书借阅系统主窗体
// * 1、实现菜单栏和工具栏的设计
// * 顺序：从上到下，从左到右*/
//public class Library extends JFrame{
//	//构造方法
//	public Library(){
//		//super();//调用父类构造方法
//
//		setSize(800,600);
//		setTitle("图书借阅系统");
//		JMenuBar menuBar=createJMenuBar();//创建菜单栏
//		setJMenuBar(menuBar);//添加菜单栏到主窗体，设置窗体菜单栏
//		//JToolBar toolBar=createJToolBar();//创建工具栏
//		//从JFrame中取得Container
//		//getContentPane().add(toolBar,BorderLayout.NORTH);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序
//		this.setVisible(true);//设置窗体显示，否则不显示。
//	}
//	private JMenuBar createJMenuBar(){
//		JMenuBar jMenuBar=new JMenuBar();//菜单栏
//
//		//读者信息管理子菜单
//		JMenu readerManageJMenu=new JMenu("读者信息管理");
//		JMenuItem readerAddJMI=new JMenuItem("读者信息添加");
//		readerAddJMI.addActionListener(new ReaderAddListener());
//		readerManageJMenu.add(readerAddJMI);
//		JMenuItem readerSelUpdJMI=new JMenuItem("读者信息查询与修改");
//		readerSelUpdJMI.addActionListener(new ReaderSelUpdListener());
//		readerManageJMenu.add(readerSelUpdJMI);
//
//		//图书信息管理子菜单
//		JMenu bookManageJMenu=new JMenu("图书信息管理");
//		JMenuItem bookAddJMI=new JMenuItem("图书信息添加");
//		bookAddJMI.addActionListener(new BookAddListener());
//		bookManageJMenu.add(bookAddJMI);
//		JMenuItem BookSelUpdJMI=new JMenuItem("图书信息查询与修改");
//		BookSelUpdJMI.addActionListener(new BookSelModListener());
//		bookManageJMenu.add(BookSelUpdJMI);
//
//		//图书借阅菜单
//		JMenu bookBorrowJMenu=new JMenu("图书借阅管理");
//		//JMenu bookBorrowSelectMenu=new JMenu("借阅查询");
//		//JMenuItem bookBorrowSelectItem=new JMenuItem("图书借阅查询");
//		//bookBorrowSelectMenu.add(bookBorrowSelectItem);
//		/*JMenuItem readerBorrowSelectItem=new JMenuItem("读者借阅查询");
//		bookBorrowSelectMenu.add(readerBorrowSelectItem);
//		bookBorrowMenu.add(bookBorrowSelectMenu);*/
//		JMenuItem bookBorrowJMI=new JMenuItem("图书借阅");
//		bookBorrowJMI.addActionListener(new BookBorrowListener());
//		bookBorrowJMenu.add(bookBorrowJMI);
//		JMenuItem bookReturnJMI=new JMenuItem("图书归还");
//		bookReturnJMI.addActionListener(new BookReturnListener());
//		bookBorrowJMenu.add(bookReturnJMI);
//
//		//基础信息维护菜单
//		JMenu baseInfoJMenu=new JMenu("基础信息维护");//新书订购菜单
//		JMenuItem readerTypeJMI=new JMenuItem("读者类别设置");
//		readerTypeJMI.addActionListener(new ReaderTypeListener());
//		baseInfoJMenu.add(readerTypeJMI);
//		JMenuItem bookTypeJMI=new JMenuItem("图书类别设置");
//		bookTypeJMI.addActionListener(new BookTypeListener());
//		baseInfoJMenu.add(bookTypeJMI);
//		JMenuItem fineSetJMI=new JMenuItem("罚金设置");
//		fineSetJMI.addActionListener(new SetfineListener());
//		baseInfoJMenu.add(fineSetJMI);
//
//		//用户管理菜单
//		JMenu userManageJMenu=new JMenu("用户管理");
//		JMenuItem updPwdJMI=new JMenuItem("修改密码");
//		updPwdJMI.addActionListener(new updatePasswordListener());
//		userManageJMenu.add(updPwdJMI);
//		JMenuItem userAddJMI=new JMenuItem("用户添加");
//		userAddJMI.addActionListener(new userAddListener());
//		userManageJMenu.add(userAddJMI);
//		JMenuItem userDelJMI=new JMenuItem("用户删除");
//		userDelJMI.addActionListener(new userDeleteListener());
//		userManageJMenu.add(userDelJMI);
//
//		//添加到菜单栏
//		jMenuBar.add(readerManageJMenu);
//		jMenuBar.add(bookManageJMenu);
//		jMenuBar.add(bookBorrowJMenu);
//		jMenuBar.add(baseInfoJMenu);
//		jMenuBar.add(userManageJMenu);
//		return jMenuBar;
//	}
//	/*private JToolBar createJToolBar(){
//		JToolBar jToolBar=new JToolBar();
//		//jToolBar.setFloatable(false);//取消工具栏浮动（不能拖到别的地方）
//		//jToolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
//		JButton jb1=new JButton(new ImageIcon("img/bookAddtb.jpg"));
//		jToolBar.add(jb1);
//		JButton jb2=new JButton(new ImageIcon("img/bookModifytb.jpg"));
//		jToolBar.add(jb2);
//		JButton jb3=new JButton(new ImageIcon("img/bookBorrowtb.jpg"));
//		jToolBar.add(jb3);
//		//图书添加工具按钮
//		JButton bookAddButton=new JButton();
//		ImageIcon bookAddIcon=new ImageIcon("img/bookAddtb.jpg");
//		bookAddButton.setIcon(bookAddIcon);
//		jToolBar.add(bookAddButton);
//		// 在工具栏中添加图书修改图标
//        JButton bookModifyButton = new JButton();
//        ImageIcon bookModifyIcon = new ImageIcon("img/bookModifytb.jpg");// 创建图标方法
//        bookModifyButton.setIcon(bookModifyIcon);
//        jToolBar.add(bookModifyButton);
//		//图书借阅工具按钮
//        JButton bookBorrowButton=new JButton();
//		ImageIcon bookBorrowIcon=new ImageIcon("img/bookBorrowtb.jpg");
//		bookBorrowButton.setIcon(bookBorrowIcon);
//		jToolBar.add(bookBorrowButton);
//		//用户添加按钮
//		JButton userAddButton=new JButton();
//		ImageIcon userAddIcon=new ImageIcon("img/userAddtb.jpg");
//		userAddButton.setIcon(userAddIcon);
//		jToolBar.add(userAddButton);
//		//用户查询与修改按钮
//		JButton userModifyButton=new JButton();
//		ImageIcon userModifyIcon=new ImageIcon("img/userModifytb.jpg");
//		userModifyButton.setIcon(userModifyIcon);
//		userModifyButton.setHideActionText(true);
//		jToolBar.add(userModifyButton);
//		//退出系统按钮
//		JButton exitButton=new JButton();
//		ImageIcon exitIcon=new ImageIcon("img/exittb.jpg");
//		exitButton.setIcon(exitIcon);
//		jToolBar.add(exitButton);
//		return jToolBar;
//	}*/
//	class BookAddListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			BookAdd bookadd=new BookAdd();
//		}
//	}
//	class BookSelModListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			BookSelectandModify bookadd=new BookSelectandModify();
//		}
//	}
//
//	class ReaderAddListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			ReaderAdd readeradd=new ReaderAdd();
//		}
//	}
//
//	class ReaderSelUpdListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			ReaderSelectandModify rsm=new ReaderSelectandModify();
//		}
//	}
//	class ReaderTypeListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			ReaderTypeManage rtm=new ReaderTypeManage();
//		}
//	}
//	class BookTypeListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			BookTypeManage rtm=new BookTypeManage();
//		}
//	}
//
//	class updatePasswordListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			UpdatePWD updpwd=new UpdatePWD();
//		}
//	}
//
//	class userAddListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			UserAdd updpwd=new UserAdd();
//		}
//	}
//	class userDeleteListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			UserDel updpwd=new UserDel();
//		}
//	}
//
//	class BookBorrowListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			BookBorrow bookborrow=new BookBorrow();
//		}
//	}
//
//	class SetfineListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			FineSet fineset=new FineSet();
//		}
//	}
//	class BookReturnListener implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			BookReturn bookreturn=new BookReturn();
//		}
//	}
//	public static void main(String[] args) {
//		new Login();
//	}
//}
