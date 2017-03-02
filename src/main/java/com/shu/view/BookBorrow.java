package com.shu.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shu.dao.BookDao;
import com.shu.dao.BorrowBookDao;
import com.shu.dao.ReaderDao;
import com.shu.entity.BookEntity;
import com.shu.entity.BorrowbookEntity;
import com.shu.entity.ReaderEntity;
import com.shu.entity.UsersEntity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;



public class BookBorrow extends JFrame {
	private JPanel selectJP,select_conditionJP,select_resultJP,borrowJP,buttonJP;
	private JTextField readeridJTF,readernameJTF,readertypeJTF,ISBNJTF,booknameJTF,authorJTF,printtimeJTF,publishJTF,publishdateJTF,unitpriceJTF,dateJTF,userJTF,booktypeJTF;
	private JLabel readeridJL,readernameJL,readertypeJL,ISBNJL,booknameJL,authorJL,categoryJL,printtimeJL,publishJL,publishdateJL,unitpriceJL,dateJL,userJL;
	private JTable table_1;

	private JFormattedTextField borrowdate;
	private JScrollPane jscrollPane;
	private UsersEntity user = Login.getUser();
	String[] borrowbooksearch = {"图书编号", "图书名称","借书日期"};
	// 取数据库中图书相关信息放入表格中
	private Object[][] getSelect(List<BorrowbookEntity> list) {

		Object[][] results = new Object[list.size()][borrowbooksearch.length];
		for (int i = 0; i < list.size(); i++) {
            BorrowbookEntity borrowbook = list.get(i);
			results[i][0] = borrowbook.getIsbn();
            Gson gson = new Gson();
            BookEntity book1 = gson.fromJson(BookDao.selectById(borrowbook.getIsbn()), BookEntity.class);
			List<BookEntity> list1 = new ArrayList<BookEntity>();
            list1.add(book1);
			for (int j = 0; j < list1.size(); j++) {
				BookEntity book = list1.get(j);
				results[i][1]=book.getBookname();
			}
			results[i][2] = borrowbook.getBorrowdate();
		}
		return results;
	}

	public BookBorrow(){

		setTitle("图书借阅");
		setBounds(100, 100, 600, 500);

		selectJP = new JPanel();
		TitledBorder tb=new TitledBorder("读者借阅信息");
		selectJP.setBorder(tb);
		selectJP.setLayout(new BorderLayout());

		//查询条件面板
		select_conditionJP = new JPanel();
		readeridJL=new JLabel("读者编号：");
		select_conditionJP.add(readeridJL);
		readeridJTF = new JTextField();
		readeridJTF.addActionListener(new ReaderidAction());
		readeridJTF.setColumns(10);
		select_conditionJP.add(readeridJTF);

		readernameJL=new JLabel("读者姓名：");
		select_conditionJP.add(readernameJL);
		readernameJTF = new JTextField();
		readernameJTF.setColumns(10);
		select_conditionJP.add(readernameJTF);

		readertypeJL=new JLabel("读者类别：");
		select_conditionJP.add(readertypeJL);
		readertypeJTF = new JTextField();
		readertypeJTF.setColumns(10);
		select_conditionJP.add(readertypeJTF);

		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		//查询结果面板
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		Object[][] results = getSelect(BorrowBookDao.selectByReaderid(readeridJTF.getText().trim()));
		table_1 = new JTable(results, borrowbooksearch);
		jscrollPane.setViewportView(table_1);
		//显示边框
		jscrollPane.setPreferredSize(new Dimension(400, 140));
		//不显示边框
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP,BorderLayout.CENTER);

		borrowJP = new JPanel();
		borrowJP.setBorder(new TitledBorder(null, "图书借阅", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

		//使用布局GridLayout
		final GridLayout gridLayout = new GridLayout(5, 4);
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		borrowJP.setLayout(gridLayout);

		ISBNJL=new JLabel("ISBN：");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(ISBNJL);
		ISBNJTF=new JTextField();
		ISBNJTF.addActionListener(new ISBNAction());
		ISBNJTF.setColumns(20);
		borrowJP.add(ISBNJTF);

		categoryJL=new JLabel("类别：");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(categoryJL);
		booktypeJTF=new JTextField();
		booktypeJTF.setColumns(20);
		borrowJP.add(booktypeJTF);

		booknameJL=new JLabel("书名：");
		booknameJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(booknameJL);
		booknameJTF=new JTextField();
		booknameJTF.setColumns(20);
		borrowJP.add(booknameJTF);

		authorJL=new JLabel("作者：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(authorJL);
		authorJTF=new JTextField();
		authorJTF.setColumns(20);
		borrowJP.add(authorJTF);

		publishJL=new JLabel("出版社：");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(publishJL);
		publishJTF=new JTextField();
		borrowJP.add(publishJTF);

		publishdateJL=new JLabel("出版日期：");
		publishdateJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(publishdateJL);
		publishdateJTF=new JTextField();
		borrowJP.add(publishdateJTF);

		printtimeJL=new JLabel("印刷次数：");
		printtimeJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(printtimeJL);
		printtimeJTF=new JTextField();
		borrowJP.add(printtimeJTF);

		unitpriceJL=new JLabel("单价：");
		unitpriceJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(unitpriceJL);
		unitpriceJTF=new JTextField();
		borrowJP.add(unitpriceJTF);

		dateJL=new JLabel("当前日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(dateJL);
		borrowdate = new JFormattedTextField(DateFormat.getDateInstance());
		borrowdate.setValue(new java.util.Date());
		borrowJP.add(borrowdate);

		userJL=new JLabel("操作人员：");
		userJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(userJL);
		userJTF=new JTextField();
		userJTF.setText(user.getName());
		userJTF.setColumns(20);
		userJTF.setEditable(false);
		borrowJP.add(userJTF);

		//按钮面板设计
		buttonJP=new JPanel();
		JButton borrowButton=new JButton("借阅");
		borrowButton.addActionListener(new BorrowBookActionListener());
		JButton closeButton=new JButton("关闭");
		closeButton.addActionListener(new CloseActionListener());
		buttonJP.add(borrowButton);
		buttonJP.add(closeButton);

		this.add(selectJP,BorderLayout.NORTH);
		this.add(borrowJP,BorderLayout.CENTER);
		this.add(buttonJP,BorderLayout.SOUTH);

		setVisible(true);
	}
	class ReaderidAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//检索读者的姓名和类型
            Gson gson = new Gson();
            ReaderEntity reader1 = gson.fromJson(ReaderDao.selectById(readeridJTF.getText().trim()), ReaderEntity.class);
			List<ReaderEntity> list = new ArrayList<ReaderEntity>();
            list.add(reader1);
			for (int i = 0; i < list.size(); i++) {
				ReaderEntity reader = list.get(i);
				readernameJTF.setText(reader.getName());
				readernameJTF.setEditable(false);
				//获取读者类型名称
				readertypeJTF.setText(reader.getType().toString());
				readertypeJTF.setEditable(false);
			}
			//检索读者的借书情况
			Object[][] results = getSelect(BorrowBookDao.selectByReaderid(readeridJTF.getText().trim()));
			table_1 = new JTable(results, borrowbooksearch);
			jscrollPane.setViewportView(table_1);
		}
	}
	//输入图书编号ISBN后回车，将图书的其余信息显示出来
	class ISBNAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
            Gson gson = new Gson();
            BookEntity book1 = gson.fromJson(BookDao.selectById(ISBNJTF.getText().trim()), BookEntity.class);
			List<BookEntity> list = new ArrayList<BookEntity>();
            list.add(book1);
			for (int i = 0; i < list.size(); i++) {
				BookEntity book = list.get(i);
				//获取图书类型名称

				booktypeJTF.setText(book.getTypeid());

				booknameJTF.setText(book.getBookname());
				authorJTF.setText(book.getAuthor());
				publishJTF.setText(book.getPublish());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date=format.format(book.getPublishdate());
				publishdateJTF.setText(date);
				String time=String.valueOf(book.getPrinttime());
				printtimeJTF.setText(time);
				String unitprice=String.valueOf(book.getUnitprice());
				unitpriceJTF.setText(unitprice);
			}
		}
	}

	class BorrowBookActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String readerid=readeridJTF.getText().trim();
			String ISBN=ISBNJTF.getText().trim();
			Date borrowtime=Date.valueOf(borrowdate.getText().trim());
            BorrowbookEntity borrowbookEntity = new BorrowbookEntity();
            borrowbookEntity.setReaderid(readerid);
            borrowbookEntity.setIsbn(ISBN);
            borrowbookEntity.setBorrowdate(borrowtime);
			int i = BorrowBookDao.borrowBook(borrowbookEntity);
			System.out.println(i);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "借阅成功");
				//借阅之后，更新读者借阅图书的信息
				Object[][] results = getSelect(BorrowBookDao.selectByReaderid(readeridJTF.getText().trim()));
				table_1 = new JTable(results, borrowbooksearch);
				jscrollPane.setViewportView(table_1);
			}
		}
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new BookBorrow();
	}
}
