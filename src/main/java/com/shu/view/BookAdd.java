package com.shu.view;

import com.shu.dao.BookDao;
import com.shu.dao.BookTypeDao;
import com.shu.entity.BookEntity;
import com.shu.entity.BooktypeEntity;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;




/*基础信息维护-》图书信息管理-》图书添加*/
public class BookAdd extends JFrame {
	private JPanel bookAddJP,buttonJP;
	private JLabel ISBNJL,booktypeJL,bookNameJL,authorJL,
			publishJL,publishDateJL,printJL,priceJL;
	private JTextField ISBNJTF,bookNameJTF,authorJTF,publishJTF,publishDateJTF,printJTF,
			priceJTF;
	private JComboBox booktypeJCB;
	private DefaultComboBoxModel booktypeModel;

	class AddBookAction implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			// 订书业务

			if (ISBNJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "ISBN不可以为空");
				return;
			}
			if (ISBNJTF.getText().length() != 10) {
				JOptionPane.showMessageDialog(null, "ISBN位数为10位");
				return;
			}
			if (bookNameJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "图书名称不可以为空");
				return;
			}
			if (authorJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "作者不可以为空");
				return;
			}
			if (publishJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "出版社不可以为空");
				return;
			}
			if (priceJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "单价不可以为空");
				return;
			}

			BookEntity book = new BookEntity();
			book.setIsbn(ISBNJTF.getText().trim());
			String typeNum;
			if((String)booktypeJCB.getSelectedItem() == "计算机类"){
				typeNum = "1";
			}
			else
			{
				typeNum = "2";
			}
			book.setTypeid(typeNum);
			book.setBookname(bookNameJTF.getText().trim());
			book.setAuthor(authorJTF.getText().trim());
			book.setPublish(publishJTF.getText().trim());
			book.setPrinttime(Integer.parseInt(printJTF.getText().trim()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date pubDate = null;
			try {
				pubDate = sdf.parse(publishDateJTF.getText().trim());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			book.setPublishdate(pubDate);
			book.setUnitprice(Double.parseDouble(priceJTF.getText().trim()));
			int i = BookDao.insert(book);

			if (i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				BookAdd.this.setVisible(false);
				//doDefaultCloseAction();
			}
		}
	}
	class ISBNFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (BookDao.selectById(ISBNJTF.getText().trim()) == null) {
				System.out.print(BookDao.selectById(ISBNJTF.getText().trim()));
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}
	public BookAdd(){
		//super();
		setBounds(200, 200, 500, 200);
		setTitle("图书信息添加");

		buttonJP=new JPanel();//按钮面板

		//图书信息添加面板设计
		bookAddJP = new JPanel();
		//bookAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		bookAddJP.setLayout(gridLayout);
		//getContentPane()
		this.add(bookAddJP);

		ISBNJL=new JLabel("ISBN：");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(ISBNJL);
		ISBNJTF=new JTextField();
		ISBNJTF.addFocusListener(new ISBNFocusListener());
		bookAddJP.add(ISBNJTF);

		booktypeJL=new JLabel("类别：");
		booktypeJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(booktypeJL);
		//下拉列表
		booktypeJCB = new JComboBox();
		booktypeModel = (DefaultComboBoxModel) booktypeJCB.getModel();
		// 从数据库中取出图书类别
		List<BooktypeEntity> list = BookTypeDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			BooktypeEntity bt = list.get(i);
            /*Item item = new Item();
            item.setId(bt.gettypeid());
            item.setName(bt.gettypename());*/
			booktypeModel.addElement(bt.getTypename());
		}
		bookAddJP.add(booktypeJCB);

		bookNameJL=new JLabel("书名：");
		bookNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(bookNameJL);
		bookNameJTF=new JTextField();
		bookAddJP.add(bookNameJTF);

		authorJL=new JLabel("作者：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(authorJL);
		authorJTF=new JTextField();
		bookAddJP.add(authorJTF);

		publishJL=new JLabel("出版社：");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(publishJL);
		publishJTF=new JTextField();
		bookAddJP.add(publishJTF);
		//publishJCB = new JComboBox();
		//bookAddJP.add(publishJCB);

		publishDateJL=new JLabel("出版日期：");
		publishDateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(publishDateJL);
		publishDateJTF=new JTextField();
		bookAddJP.add(publishDateJTF);

		printJL=new JLabel("印刷次数：");
		printJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(printJL);
		printJTF=new JTextField();
		bookAddJP.add(printJTF);

		priceJL=new JLabel("单价：");
		priceJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(priceJL);
		priceJTF=new JTextField();
		bookAddJP.add(priceJTF);

		//登录取消按钮面板设计
		JButton addButton=new JButton("添加");
		addButton.addActionListener(new AddBookAction());
		JButton resetButton=new JButton("重置");
		resetButton.addActionListener(new ResetBookAction());
		JButton closeButton=new JButton("关闭");
		closeButton.addActionListener(new CloseActionListener());
		buttonJP.add(addButton);
		buttonJP.add(resetButton);
		buttonJP.add(closeButton);

		this.add(bookAddJP,BorderLayout.CENTER);
		this.add(buttonJP,BorderLayout.SOUTH);

		this.setVisible(true);//设置窗体显示，否则不显示。
		setResizable(false);//取消最大化
	}
	class ResetBookAction implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			ISBNJTF.setText(null);
			bookNameJTF.setText(null);
			authorJTF.setText(null);
			publishJTF.setText(null);
			publishDateJTF.setText(null);
			printJTF.setText(null);
			priceJTF.setText(null);
		}
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器

		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new BookAdd();
	}

}
