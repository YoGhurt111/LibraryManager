//package com.shu.view;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JFormattedTextField;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.TitledBorder;
//
//
//
//public class BookReturn extends JFrame {
//	private JTextField readeridJTF,readernameJTF,readertypeJTF,ISBNJTF,booktypeJTF,booknameJTF,authorJTF,printtimeJTF,publishJTF,publishdateJTF,unitpriceJTF,dateJTF,userJTF,overlimitJTF,fineJTF;
//	private JLabel readeridJL,readernameJL,readertypeJL,ISBNJL,booknameJL,authorJL,categoryJL,printtimeJL,publishJL,publishdateJL,unitpriceJL,dateJL,userJL,overlimitJL,fineJL;
//	private JTable table;
//	private DefaultComboBoxModel booktypeModel;
//	private JFormattedTextField returndate;
//	private JScrollPane scrollPane;
//	private Users user = Login.getUser();
//	String[] borrowbooksearch = {"图书编号", "图书名称","借书日期"};
//	// 取数据库中图书相关信息放入表格中
//	private Object[][] getSelect(List<BorrowBook> list) {
//
//		Object[][] results = new Object[list.size()][borrowbooksearch.length];
//		for (int i = 0; i < list.size(); i++) {
//			BorrowBook borrowbook = list.get(i);
//			results[i][0] = borrowbook.getISBN();
//			List<Book> list1=BookDao.selectBookByISBN(borrowbook.getISBN());
//			for (int j = 0; j < list1.size(); j++) {
//				Book book = list1.get(j);
//				results[i][1]=book.getBookname();
//			}
//			results[i][2] = borrowbook.getBorrowdate();
//		}
//		return results;
//	}
//
//	public BookReturn(){
//		super();
//
//		setTitle("图书归还");
//		setBounds(100, 100, 600, 500);
//
//		final JPanel selectpanel = new JPanel();
//		selectpanel.setBorder(new TitledBorder(null, "读者借阅信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
//		selectpanel.setLayout(new BorderLayout());
//
//		//查询条件面板
//		final JPanel selectpanel_condition = new JPanel();
//		readeridJL=new JLabel("读者编号：");
//		selectpanel_condition.add(readeridJL);
//		readeridJTF = new JTextField();
//		readeridJTF.addActionListener(new ReaderidAction());
//		readeridJTF.setColumns(10);
//		selectpanel_condition.add(readeridJTF);
//
//		readernameJL=new JLabel("读者姓名：");
//		selectpanel_condition.add(readernameJL);
//		readernameJTF = new JTextField();
//		readernameJTF.setColumns(10);
//		selectpanel_condition.add(readernameJTF);
//
//		readertypeJL=new JLabel("读者类别：");
//		selectpanel_condition.add(readertypeJL);
//		readertypeJTF = new JTextField();
//		readertypeJTF.setColumns(10);
//		selectpanel_condition.add(readertypeJTF);
//
//		selectpanel.add(selectpanel_condition, BorderLayout.NORTH);
//		//查询结果面板
//		final JPanel selectpanel_result = new JPanel();
//		scrollPane = new JScrollPane();
//
//		//显示边框
//		scrollPane.setPreferredSize(new Dimension(400, 140));
//		selectpanel_result.add(scrollPane);
//		selectpanel.add(selectpanel_result,BorderLayout.CENTER);
//
//		final JPanel JPanel_borrow = new JPanel();
//		JPanel_borrow.setBorder(new TitledBorder(null, "图书归还", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
//
//		//final JPanel JPanel_borrow = new JPanel();
//		//使用布局GridLayout
//		final GridLayout gridLayout = new GridLayout(6, 4);
//		gridLayout.setVgap(8);
//		gridLayout.setHgap(8);
//		JPanel_borrow.setLayout(gridLayout);
//
//		ISBNJL=new JLabel("ISBN：");
//		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(ISBNJL);
//		ISBNJTF=new JTextField();
//		ISBNJTF.setColumns(20);
//		JPanel_borrow.add(ISBNJTF);
//
//		categoryJL=new JLabel("类别：");
//		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(categoryJL);
//		//下拉列表
//		booktypeJTF = new JTextField();
//		booktypeJTF.setColumns(20);
//		JPanel_borrow.add(booktypeJTF);
//
//		booknameJL=new JLabel("书名：");
//		booknameJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(booknameJL);
//		booknameJTF=new JTextField();
//		booknameJTF.setColumns(20);
//		JPanel_borrow.add(booknameJTF);
//
//		authorJL=new JLabel("作者：");
//		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(authorJL);
//		authorJTF=new JTextField();
//		authorJTF.setColumns(20);
//		JPanel_borrow.add(authorJTF);
//
//		publishJL=new JLabel("出版社：");
//		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(publishJL);
//		publishJTF=new JTextField();
//		JPanel_borrow.add(publishJTF);
//
//		publishdateJL=new JLabel("出版日期：");
//		publishdateJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(publishdateJL);
//		publishdateJTF=new JTextField();
//		JPanel_borrow.add(publishdateJTF);
//
//		printtimeJL=new JLabel("印刷次数：");
//		printtimeJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(printtimeJL);
//		printtimeJTF=new JTextField();
//		JPanel_borrow.add(printtimeJTF);
//
//		unitpriceJL=new JLabel("单价：");
//		unitpriceJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(unitpriceJL);
//		unitpriceJTF=new JTextField();
//		JPanel_borrow.add(unitpriceJTF);
//
//		dateJL=new JLabel("当前日期：");
//		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(dateJL);
//		returndate = new JFormattedTextField(DateFormat.getDateInstance());
//		returndate.setValue(new java.util.Date());
//		JPanel_borrow.add(returndate);
//
//		overlimitJL=new JLabel("超期天数：");
//		overlimitJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(overlimitJL);
//		overlimitJTF=new JTextField();
//
//		JPanel_borrow.add(overlimitJTF);
//
//		fineJL=new JLabel("罚金：");
//		fineJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(fineJL);
//		fineJTF=new JTextField();
//
//		JPanel_borrow.add(fineJTF);
//
//		userJL=new JLabel("操作人员：");
//		userJL.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel_borrow.add(userJL);
//		userJTF=new JTextField();
//		userJTF.setText(user.getName());
//		userJTF.setColumns(20);
//		userJTF.setEditable(false);
//		JPanel_borrow.add(userJTF);
//
//		//按钮面板设计
//		JPanel buttonPanel=new JPanel();
//		JButton returnButton=new JButton("归还");
//		returnButton.addActionListener(new ReturnBookActionListener());
//		JButton closeButton=new JButton("关闭");
//		closeButton.addActionListener(new CloseActionListener());
//		buttonPanel.add(returnButton);
//		buttonPanel.add(closeButton);
//
//		this.add(selectpanel,BorderLayout.NORTH);
//		this.add(JPanel_borrow,BorderLayout.CENTER);
//		this.add(buttonPanel,BorderLayout.SOUTH);
//
//		setVisible(true);
//	}
//	class ReaderidAction implements ActionListener{
//
//		public void actionPerformed(ActionEvent arg0) {
//			//检索读者的姓名和类型
//			List<Reader> list=ReaderDao.selectReaderById(readeridJTF.getText().trim());
//			for (int i = 0; i < list.size(); i++) {
//				Reader reader = list.get(i);
//				readernameJTF.setText(reader.getName());
//				readernameJTF.setEditable(false);
//
//				readertypeJTF.setText(reader.getTypename());
//				readertypeJTF.setEditable(false);
//			}
//			//检索读者的借书情况
//			Object[][] results = getSelect(BookBorrowDao.selectBorrowBookByReaderId(readeridJTF.getText().trim()));
//			table = new JTable(results, borrowbooksearch);
//			table.addMouseListener(new TableListener());
//			scrollPane.setViewportView(table);
//		}
//	}
//	//点击上面的借阅图书信息表格，将借阅的图书的信息显示出来
//	class TableListener extends MouseAdapter {
//
//		public void mouseClicked(final MouseEvent e) {
//
//			int selRow = table.getSelectedRow();
//			ISBNJTF.setText(table.getValueAt(selRow, 0).toString().trim());
//			System.out.println(ISBNJTF.getText());
//			List<Book> list=BookDao.selectBookByISBN(ISBNJTF.getText().trim());
//			for (int i = 0; i < list.size(); i++) {
//				Book book = list.get(i);
//				//获取图书类型名称
//
//				booktypeJTF.setText(book.getTypename());
//				booknameJTF.setText(book.getBookname());
//				authorJTF.setText(book.getAuthor());
//				publishJTF.setText(book.getPublish());
//				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//				String date=format.format(book.getPublishdate());
//				publishdateJTF.setText(date);
//				String time=String.valueOf(book.getPrinttime());
//				printtimeJTF.setText(time);
//				String unitprice=String.valueOf(book.getUnitprice());
//				unitpriceJTF.setText(unitprice);
//			}
//			//设置超期天数值
//			java.sql.Date borrowday,returnday;
//			borrowday =java.sql.Date.valueOf(table.getValueAt(selRow, 2).toString().trim());
//			returnday=java.sql.Date.valueOf(returndate.getText().trim());
//			Long m_intervalday = returnday.getTime() - borrowday.getTime();//计算所得为微秒数
//			Long borrowtime = m_intervalday / 1000 / 60 / 60 / 24;//计算所得的天数
//			System.out.println(borrowtime);
//			List<Reader> list1=ReaderDao.selectReaderById(readeridJTF.getText().trim());
//			//for()
//			int limit;
//			for (int i = 0; i < list1.size(); i++) {
//				Reader reader = list1.get(i);
//				limit=reader.getLimit();
//
//				if(borrowtime>limit){
//					overlimitJTF.setText(String.valueOf(borrowtime));
//					Double zfk=Double.valueOf(borrowtime)*FineSet.fine;
//					fineJTF.setText(String.valueOf(zfk));
//				}
//				else{
//					overlimitJTF.setText("没有超过规定天数");
//					fineJTF.setText("0");
//				}
//			}
//		}
//
//	}
//
//
//	class ReturnBookActionListener implements ActionListener{
//		public void actionPerformed(ActionEvent arg0) {
//			String readerid=readeridJTF.getText().trim();
//			String ISBN=ISBNJTF.getText().trim();
//			java.sql.Date returntime=java.sql.Date.valueOf(returndate.getText().trim());
//
//			int i = BookBorrowDao.returnBook(readerid,ISBN,returntime);
//			System.out.println(i);
//			if (i == 1) {
//				JOptionPane.showMessageDialog(null, "归还成功");
//				//归还之后，更新读者借阅图书的信息
//				Object[][] results = getSelect(BookBorrowDao.selectBorrowBookByReaderId(readeridJTF.getText().trim()));
//				table = new JTable(results, borrowbooksearch);
//				scrollPane.setViewportView(table);
//			}
//		}
//	}
//	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			setVisible(false);
//		}
//	}
//
//	/*public static void main(String[] args) {
//		new BookReturn();
//	}*/
//}
