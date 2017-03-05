package com.shu.view;

import com.google.gson.Gson;
import com.shu.dao.*;
import com.shu.entity.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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



public class BookReturn extends JFrame {
	private JTextField select_condition1JTF,select_condition2JTF,readeridJTF,readernameJTF,readertypeJTF,ISBNJTF,booktypeJTF,booknameJTF,authorJTF,printtimeJTF,publishJTF,publishdateJTF,unitpriceJTF,dateJTF,userJTF,overlimitJTF,fineJTF;
	private JLabel bookJL,readerJL,readeridJL,readernameJL,readertypeJL,ISBNJL,booknameJL,authorJL,categoryJL,printtimeJL,publishJL,publishdateJL,unitpriceJL,dateJL,userJL,overlimitJL,fineJL;
	private JTable table;
	private DefaultComboBoxModel booktypeModel;
	private JFormattedTextField returndate;
	private JScrollPane scrollPane;
	private String readeridForReturn;
	private String isbnForReturn;
	private UsersEntity user = Login.getUser();
	String[] borrowbooksearch = {"图书编号", "读者编号","借书日期"};
	// 取数据库中图书相关信息放入表格中
	private Object[][] getSelect(List<BorrowbookEntity> list) {
		Object[][] results = new Object[list.size()][borrowbooksearch.length];
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			BorrowbookEntity borrowbook = list.get(i);
			if (borrowbook.getReturndate() == null) {
				results[j][0] = borrowbook.getIsbn();
				results[j][1] = borrowbook.getReaderid();
				results[j][2] = borrowbook.getBorrowdate();
				j++;
			}
		}
		Object[][] results1 = new Object[j][borrowbooksearch.length];
		for (int i = 0; i < j; i++){
			results1[i][0] = results[i][0];
			results1[i][1] = results[i][1];
			results1[i][2] = results[i][2];
		}
		return results1;
	}

	public BookReturn(){
		super();

		setTitle("图书归还");
		setBounds(100, 100, 400, 380);

		isbnForReturn = null;
		readeridForReturn = null;

		final JPanel selectpanel = new JPanel();
		TitledBorder tb=new TitledBorder("图书归还");
		selectpanel.setBorder(tb);
		selectpanel.setLayout(new BorderLayout());

		//查询条件面板
		final JPanel selectpanel_condition = new JPanel();
		final GridLayout gridLayout = new GridLayout(2,3);
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		selectpanel_condition.setLayout(gridLayout);

		bookJL=new JLabel("图书编号：");
		bookJL.setHorizontalAlignment(SwingConstants.CENTER);
		selectpanel_condition.add(bookJL);
		select_condition1JTF= new JTextField();
		select_condition1JTF.setColumns(10);
		selectpanel_condition.add(select_condition1JTF);
		JButton button_select1 = new JButton();
		button_select1.setText("查询");
		button_select1.addActionListener(new SelectAction1());
		selectpanel_condition.add(button_select1);
		selectpanel.add(selectpanel_condition, BorderLayout.NORTH);

		readerJL=new JLabel("读者编号：");
		readerJL.setHorizontalAlignment(SwingConstants.CENTER);
		selectpanel_condition.add(readerJL);
		select_condition2JTF= new JTextField();
		select_condition2JTF.setColumns(10);
		selectpanel_condition.add(select_condition2JTF);
		JButton button_select2 = new JButton();
		button_select2.setText("查询");
		button_select2.addActionListener(new SelectAction2());
		selectpanel_condition.add(button_select2);
		selectpanel.add(selectpanel_condition, BorderLayout.NORTH);


		//查询结果面板
		JPanel selectpanel_result = new JPanel();
		scrollPane = new JScrollPane();
		Object[][] results = getSelect(BorrowBookDao.selectAll());
		table = new JTable(results, borrowbooksearch);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.addMouseListener(new TableListener());
		scrollPane.setPreferredSize(new Dimension(300,150));
		selectpanel_result.add(scrollPane);
		selectpanel.add(selectpanel_result,BorderLayout.CENTER);

		final JPanel borrowJP = new JPanel();
		final GridLayout gridLayout2 = new GridLayout(3,2);
		gridLayout2.setVgap(8);
		gridLayout2.setHgap(8);
		borrowJP.setLayout(gridLayout2);

		dateJL=new JLabel("当前日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(dateJL);
		returndate = new JFormattedTextField(DateFormat.getDateInstance());
		returndate.setValue(new java.util.Date());
		borrowJP.add(returndate);

		fineJL=new JLabel("罚金：");
		fineJL.setHorizontalAlignment(SwingConstants.CENTER);
		borrowJP.add(fineJL);
		fineJTF=new JTextField();
		borrowJP.add(fineJTF);

		JButton returnButton=new JButton("归还");
		borrowJP.add(returnButton);
		returnButton.addActionListener(new ReturnBookActionListener());
		JButton closeButton=new JButton("关闭");
		closeButton.addActionListener(new CloseActionListener());
		borrowJP.add(closeButton);

		this.add(selectpanel,BorderLayout.NORTH);
		this.add(selectpanel_result,BorderLayout.CENTER);
		this.add(borrowJP,BorderLayout.SOUTH);

		setVisible(true);
	}

class SelectAction1 implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		Object[][] results = getSelect(BorrowBookDao.selectByIsbn(select_condition1JTF.getText().trim()));
		table= new JTable(results, borrowbooksearch);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(new TableListener());
	}
}
class SelectAction2 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Object[][] results = getSelect(BorrowBookDao.selectByReaderid(select_condition2JTF.getText().trim()));
			table= new JTable(results, borrowbooksearch);
			scrollPane.setViewportView(table);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.addMouseListener(new TableListener());
		}
	}


	//点击上面的借阅图书信息表格，将借阅的图书的信息显示出来
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			isbnForReturn = table.getValueAt(selRow, 0).toString().trim();
			readeridForReturn = table.getValueAt(selRow, 1).toString().trim();

			//设置超期天数值
			Date borrowday,returnday;
			borrowday =(Date)table.getValueAt(selRow, 2);
			returnday=java.sql.Date.valueOf(returndate.getText().trim());
			Long m_intervalday = returnday.getTime() - borrowday.getTime();//计算所得为微秒数
			Long borrowtime = m_intervalday / 1000 / 60 / 60 / 24;//计算所得的天数
            Gson gson1 = new Gson();
            ReaderEntity readerEntity = gson1.fromJson(ReaderDao.selectById(table.getValueAt(selRow, 1).toString()), ReaderEntity.class);

			//for()
			int limit;
			ReadertypeEntity readertypeEntity = gson1.fromJson(ReaderTypeDao.selectById(readerEntity.getType()), ReadertypeEntity.class);
			limit=readertypeEntity.getLimitday();

			if(borrowtime>limit){
				overlimitJTF.setText(String.valueOf(borrowtime));
				Double zfk=Double.valueOf(borrowtime)*5;
				fineJTF.setText(String.valueOf(zfk));
			}
			else{
				fineJTF.setText("0");
			}
		}

	}


	class ReturnBookActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date returntime = null;
			try {
				returntime = sdf.parse(returndate.getText().toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int i = BorrowBookDao.returnBook(readeridForReturn,isbnForReturn,returntime);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "归还成功");
				//归还之后，更新读者借阅图书的信息
				setVisible(false);
				new BookReturn();
//
			}
		}
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}



}
