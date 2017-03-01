package com.shu.view;

import com.shu.dao.BookDao;
import com.shu.entity.BookEntity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;







public class BookSelectandModify extends JFrame {
	private JTabbedPane tabbedPane;
	private JPanel buttonJP;

	private JTextField selectJTF,ISBNJTF,booknameJTF,authorJTF,printtimeJTF,publishJTF,publishdateJTF,unitpriceJTF;
	private JLabel ISBNJL,booknameJL,authorJL,categoryJL,printtimeJL,publishJL,publishdateJL,unitpriceJL;

	private JTable table_1;

	private JComboBox choice,booktypeJCB;
	private DefaultComboBoxModel booktypeModel;

	private JScrollPane jscrollPane;
	String booksearch[] = { "编号", "分类", "名称", "作者", "出版社", "出版日期", "版次", "单价" };
	// 取数据库中图书相关信息放入表格中
	private Object[][] getSelect(List<BookEntity> list) {
		String[] columnNames = { "图书编号", "图书类别", "图书名称", "作者", "出版社", "出版日期", "印刷次数", "单价" };
		Object[][] results = new Object[list.size()][columnNames.length];

		for (int i = 0; i < list.size(); i++) {
            BookEntity book = list.get(i);
			results[i][0] = book.getIsbn();
			results[i][1] = book.getTypeid();
			results[i][2] = book.getBookname();
			results[i][3] = book.getAuthor();
			results[i][4] = book.getPublish();
			results[i][5] = book.getPublishdate();
			results[i][6] = book.getPrinttime();
			results[i][7] = book.getUnitprice();
		}
		return results;
	}
	class SelectAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name = (String) choice.getSelectedItem();
            List<BookEntity> bookList =
			if (name.equals("ISBN")) {
				Object[][] results = getSelect(BookDao.selectById(selectJTF.getText().trim()));
				table_1 = new JTable(results, booksearch);
				jscrollPane.setViewportView(table_1);
			} else if (name.equals("图书名称")) {

				Object[][] results = getSelect(BookDao.selectBookByName(selectJTF.getText()));
				table_1 = new JTable(results, booksearch);
				jscrollPane.setViewportView(table_1);
			}else if (name.equals("图书类别")) {

				Object[][] results = getSelect(BookDao.selectBookByType(selectJTF.getText()));
				table_1 = new JTable(results, booksearch);
				jscrollPane.setViewportView(table_1);
			}else if (name.equals("作者")) {

				Object[][] results = getSelect(BookDao.selectBookByAuthor(selectJTF.getText()));
				table_1 = new JTable(results, booksearch);
				jscrollPane.setViewportView(table_1);
			}else if (name.equals("出版社")) {

				Object[][] results = getSelect(BookDao.selectBookByPublish(selectJTF.getText()));
				table_1 = new JTable(results, booksearch);
				jscrollPane.setViewportView(table_1);
			}
		}
	}
	class ISBNAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			List<Book> list=BookDao.selectBookByISBN(ISBNJTF.getText().trim());
			for (int i = 0; i < list.size(); i++) {
				Book book = list.get(i);
				booktypeJCB.setSelectedItem(book.getTypeid());
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
	class UpdateBookAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String ISBN=ISBNJTF.getText().trim();
			String author = authorJTF.getText().trim();
			String bookName = booknameJTF.getText().trim();
			String booktype=(String)booktypeJCB.getSelectedItem();
			String publish = publishJTF.getText().trim();
			String publishdate = publishdateJTF.getText().trim();
			Integer printtime =Integer.parseInt(printtimeJTF.getText().trim());
			Double unitprice = Double.parseDouble(unitpriceJTF.getText().trim());
			int i = BookDao.updatebook(ISBN,booktype,bookName, author, publish, publishdate, printtime, unitprice);
			System.out.println(i);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		}
	}

	public BookSelectandModify(){
		super();

		setTitle("图书查询与修改");
		setBounds(100, 100, 500, 400);

		//JTabbedPan选项卡
		tabbedPane = new JTabbedPane();
		add(tabbedPane);

		final JPanel selectpanel = new JPanel();
		selectpanel.setLayout(new BorderLayout());
		tabbedPane.addTab("图书信息查询", null, selectpanel, null);
		//查询条件面板
		final JPanel selectpanel_condition = new JPanel();
		choice = new JComboBox();
		String[] array = { "ISBN","图书名称","图书类别","作者","出版社" };
		for (int i = 0; i < array.length; i++) {
			choice.addItem(array[i]);
		}

		selectpanel_condition.add(choice);
		selectJTF = new JTextField();
		selectJTF.setColumns(20);
		selectpanel_condition.add(selectJTF);
		selectpanel.add(selectpanel_condition, BorderLayout.NORTH);
		//查询结果面板
		final JPanel selectpanel_result = new JPanel();
		jscrollPane = new JScrollPane();
		Object[][] results = getSelect(BookDao.selectBook());
		String[] booksearch = { "编号", "分类", "名称", "作者", "出版社", "出版日期", "出版次数", "单价" };
		table_1 = new JTable(results, booksearch);
		jscrollPane.setViewportView(table_1);
		//显示边框
		jscrollPane.setPreferredSize(new Dimension(400, 240));
		//不显示边框
		//scrollPane_1.setSize(400,200);
		selectpanel_result.add(jscrollPane);
		selectpanel.add(selectpanel_result,BorderLayout.CENTER);
		//查询按钮面板
		buttonJP = new JPanel();
		final JButton button_select = new JButton();
		button_select.setText("查询");
		buttonJP.add(button_select);
		final JButton button_exit = new JButton();
		button_exit.setText("退出");
		buttonJP.add(button_exit);
		selectpanel.add(buttonJP, BorderLayout.SOUTH);

		button_select.addActionListener(new SelectAction()) ;
		button_exit.addActionListener(new CloseActionListener());

		final JPanel JPanel_update = new JPanel();
		tabbedPane.addTab("图书信息修改", null, JPanel_update, null);

		final JPanel bookJP = new JPanel();
		//使用布局GridLayout
		final GridLayout gridLayout = new GridLayout(8, 2);
		gridLayout.setVgap(8);
		gridLayout.setHgap(8);
		bookJP.setLayout(gridLayout);

		ISBNJL=new JLabel("ISBN：");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(ISBNJL);
		ISBNJTF=new JTextField();
		ISBNJTF.addActionListener(new ISBNAction());
		ISBNJTF.setColumns(20);
		bookJP.add(ISBNJTF);

		categoryJL=new JLabel("类别：");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(categoryJL);
		//下拉列表
		booktypeJCB = new JComboBox();
		booktypeModel = (DefaultComboBoxModel) booktypeJCB.getModel();
		// 从数据库中取出图书类别
		List<BookType> list = BookTypeDao.selectBookType();
		for (int i = 0; i < list.size(); i++) {
			BookType bt = list.get(i);
			booktypeModel.addElement(bt.gettypename());
		}
		bookJP.add(booktypeJCB);

		booknameJL=new JLabel("书名：");
		booknameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(booknameJL);
		booknameJTF=new JTextField();
		booknameJTF.setColumns(20);
		bookJP.add(booknameJTF);

		authorJL=new JLabel("作者：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(authorJL);
		authorJTF=new JTextField();
		authorJTF.setColumns(20);
		bookJP.add(authorJTF);

		publishJL=new JLabel("出版社：");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(publishJL);
		publishJTF=new JTextField();
		bookJP.add(publishJTF);
		//publishJCB = new JComboBox();
		//bookJP.add(publishJCB);

		publishdateJL=new JLabel("出版日期：");
		publishdateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(publishdateJL);
		publishdateJTF=new JTextField();
		bookJP.add(publishdateJTF);

		printtimeJL=new JLabel("印刷次数：");
		printtimeJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(printtimeJL);
		printtimeJTF=new JTextField();
		bookJP.add(printtimeJTF);

		unitpriceJL=new JLabel("单价：");
		unitpriceJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookJP.add(unitpriceJL);
		unitpriceJTF=new JTextField();
		bookJP.add(unitpriceJTF);

		//按钮面板设计
		JPanel buttonPanel=new JPanel();
		JButton updateButton=new JButton("修改");
		updateButton.addActionListener(new UpdateBookAction());
		JButton resetButton=new JButton("关闭");
		resetButton.addActionListener(new CloseActionListener());
		buttonPanel.add(updateButton);
		buttonPanel.add(resetButton);

		JPanel_update.add(bookJP,BorderLayout.CENTER);
		JPanel_update.add(buttonPanel,BorderLayout.SOUTH);

		setVisible(true);
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BookSelectandModify();
	}
}
