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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class BookBorrow extends JFrame {
	private JPanel selectJP,select_resultJP,borrowJP;
	private JTextField select_condition1JTF,select_condition2JTF;
	private JLabel bookJL,readerJL,dateJL;
	private JTable table;
	private DefaultTableModel model;
	private JFormattedTextField borrowdate;
	private JScrollPane jscrollPane;
	private UsersEntity user = Login.getUser();
	private String[] borrowbooksearch = {"图书编号", "图书类别","图书名称","作者"};
	private String isbnForSelect;

	// 取数据库中图书相关信息放入表格中
	private Object[][] getSelect(List<BookEntity> list) {
		try {
			Object[][] results = new Object[list.size()][borrowbooksearch.length];
			for (int i = 0; i < list.size(); i++) {
				BookEntity book = list.get(i);
				results[i][0] = book.getIsbn();
				results[i][1] = book.getTypeid();
				results[i][2] = book.getBookname();
				results[i][3] = book.getAuthor();
			}
			return results;
		}catch (NullPointerException e){
			return null;
		}
	}

	public BookBorrow(){

		setTitle("图书借阅");
		setBounds(100, 100, 400, 330);

		selectJP = new JPanel();
		TitledBorder tb=new TitledBorder("读者借阅信息");
		selectJP.setBorder(tb);
		selectJP.setLayout(new BorderLayout());

		//用于借阅的isbn
		isbnForSelect = null;

		//查询条件面板
		JPanel select_conditionJP = new JPanel();
		final GridLayout gridLayout = new GridLayout(2,3);
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		select_conditionJP.setLayout(gridLayout);

		bookJL=new JLabel("图书编号");
		bookJL.setHorizontalAlignment(SwingConstants.CENTER);
		select_conditionJP.add(bookJL);
		//查询条件文本框
		select_condition1JTF = new JTextField();
		select_condition1JTF.setColumns(20);
		select_conditionJP.add(select_condition1JTF);
		//查询条件按钮
		JButton button_select1 = new JButton();
		button_select1.setText("查询");
		button_select1.addActionListener(new SelectAction());
		select_conditionJP.add(button_select1);

		readerJL=new JLabel("读者编号");
		readerJL.setHorizontalAlignment(SwingConstants.CENTER);
		select_conditionJP.add(readerJL);
		//查询条件文本框
		select_condition2JTF = new JTextField();
		select_condition2JTF.setColumns(20);
		select_conditionJP.add(select_condition2JTF);
		//查询条件按钮
		JButton button_select2 = new JButton();
		button_select2.setText("借阅");
		button_select2.addActionListener(new BorrowAction());
		select_conditionJP.add(button_select2);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);

		//查询结果面板
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
	    Object[][] results = getSelect(BookDao.selectAll());
	    table = new JTable(results, borrowbooksearch);
		jscrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new TableListener());
		jscrollPane.setPreferredSize(new Dimension(300,150));
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP,BorderLayout.CENTER);

		borrowJP=new JPanel();
		dateJL=new JLabel("当前日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(dateJL);
		borrowdate = new JFormattedTextField(DateFormat.getDateInstance());
		borrowdate.setValue(new java.util.Date());
		borrowJP.add(borrowdate);
		borrowJP.setVisible(true);

		JButton closeButton=new JButton("关闭");
		closeButton.addActionListener(new CloseActionListener());
		borrowJP.add(closeButton);

		this.add(selectJP,BorderLayout.NORTH);
		this.add(select_resultJP,BorderLayout.CENTER);
		this.add(borrowJP,BorderLayout.SOUTH);
		setVisible(true);
	}

	class BorrowAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String readerid = select_condition2JTF.getText().trim();
			if(isbnForSelect == null){

				JOptionPane.showMessageDialog(null, "您需要选择借阅的书籍");
			}
			else if (readerid.equals("")){
				JOptionPane.showMessageDialog(null, "您需要填入借阅者的编号");
			}
			else {
				Date borrowtime = Date.valueOf(borrowdate.getText().trim());
				BorrowbookEntity borrowbookEntity = new BorrowbookEntity();
				borrowbookEntity.setReaderid(readerid);
				borrowbookEntity.setIsbn(isbnForSelect);
				borrowbookEntity.setBorrowdate(borrowtime);
				int i = BorrowBookDao.borrowBook(borrowbookEntity);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "借阅成功");
				}
			}
		}
	}
	class SelectAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Gson gson = new Gson();
			List<BookEntity> bookList = new ArrayList<BookEntity>();
			bookList.add(gson.fromJson(BookDao.selectById(select_condition1JTF.getText().trim()), BookEntity.class));
			Object[][] results = getSelect(bookList);
			if(results == null){
				table= new JTable(new Object[0][4], borrowbooksearch);
				jscrollPane.setViewportView(table);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.addMouseListener(new TableListener());
			}
			else {
				table = new JTable(results, borrowbooksearch);
				jscrollPane.setViewportView(table);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.addMouseListener(new TableListener());
			}
		}
	}

		class TableListener extends MouseAdapter {
			public void mouseClicked(final MouseEvent e) {
				int selRow = table.getSelectedRow();
				isbnForSelect = table.getValueAt(selRow, 0).toString().trim();
			}
		}

		class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
			public void actionPerformed(final ActionEvent e) {
				setVisible(false);
			}
		}

}
