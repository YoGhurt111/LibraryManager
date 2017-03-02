package com.shu.view;

import com.google.gson.Gson;
import com.shu.dao.ReaderDao;
import com.shu.dao.ReaderTypeDao;
import com.shu.entity.ReaderEntity;
import com.shu.entity.ReadertypeEntity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;




public class ReaderSelectandModify extends JFrame{
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel IDJL,typeJL,readerNameJL,sexJL,phoneJL,deptJL,ageJL,regJL;
	private JTextField select_conditionJTF,IDJTF,readerNameJTF,phoneJTF,deptJTF,ageJTF,regJTF;
	private JComboBox conditionJCB,readertypeJCB;
	private JScrollPane jscrollPane;
	private DefaultComboBoxModel readertypeModel;
	final JRadioButton radioButton1,radioButton2;
	private JTable jtable;

	private String[] readersearch = { "编号", "类型", "姓名","年龄", "性别", "电话", "系部", "注册日期" };
	private Object[][] getSelect(List<ReaderEntity> list) {
		Object[][] results = new Object[list.size()][readersearch.length];
		for (int i = 0; i < list.size(); i++) {
			ReaderEntity reader = list.get(i);

			results[i][0] = reader.getReaderid();
			results[i][1] = reader.getType();
			results[i][2] = reader.getName();
			results[i][3] = reader.getAge();
			results[i][4] =	reader.getSex();
			results[i][5] = reader.getPhone();
			results[i][6] = reader.getDept();
			results[i][7] = reader.getRegdate();
		}
		return results;
	}

	public ReaderSelectandModify(){
		//super();
		setBounds(200, 200, 500, 500);
		setTitle("读者信息查询与修改");

		//读者信息查询面板设计
		JPanel selectpanel = new JPanel();
		selectpanel.setLayout(new BorderLayout());

		//查询条件面板
		//查询条件下拉列表框
		final JPanel select_conditionJP = new JPanel();
		conditionJCB = new JComboBox();
		String[] array = { "读者编号","姓名","类型","系部"};
		for (int i = 0; i < array.length; i++) {
			conditionJCB.addItem(array[i]);
		}
		select_conditionJP.add(conditionJCB);
		//查询条件文本框
		select_conditionJTF = new JTextField();
		select_conditionJTF.setColumns(20);
		select_conditionJP.add(select_conditionJTF);
		//查询条件按钮
		JButton button_select = new JButton();
		button_select.setText("查询");
		button_select.addActionListener(new SelectAction());
		select_conditionJP.add(button_select);

		selectpanel.add(select_conditionJP, BorderLayout.NORTH);

		//查询结果面板
		JPanel select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));
		//DefaultTableModel model = new DefaultTableModel();
		Object[][] results = getSelect(ReaderDao.selectAll());
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtable.addMouseListener(new TableListener());
		jscrollPane.setViewportView(jtable);

		select_resultJP.add(jscrollPane);
		selectpanel.add(select_resultJP,BorderLayout.CENTER);

		//读者信息修改面板设计
		JPanel readerUpdPanel = new JPanel();
		readerUpdPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		readerUpdPanel.setLayout(gridLayout);
		//getContentPane().add(readerUpdPanel);

		IDJL=new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(IDJL);
		IDJTF=new JTextField();
		readerUpdPanel.add(IDJTF);

		readerNameJL=new JLabel("姓名：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(readerNameJL);
		readerNameJTF=new JTextField();
		readerUpdPanel.add(readerNameJTF);

		typeJL=new JLabel("类别：");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(typeJL);
		//下拉列表
		readertypeJCB = new JComboBox();
		readertypeModel = (DefaultComboBoxModel) readertypeJCB.getModel();
		// 从数据库中取出图书类别
		List<ReadertypeEntity> list = ReaderTypeDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			ReadertypeEntity rt = list.get(i);
			readertypeModel.addElement(rt.getTypename());
		}
		readerUpdPanel.add(readertypeJCB);

		sexJL=new JLabel("性别：");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(sexJL);
		final JPanel label_sex = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		label_sex.setLayout(flowLayout);
		readerUpdPanel.add(label_sex);


		radioButton1 = new JRadioButton();
		label_sex.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		radioButton1.setText("男");

		radioButton2 = new JRadioButton();
		label_sex.add(radioButton2);
		buttonGroup.add(radioButton2);
		radioButton2.setText("女");

		ageJL=new JLabel("年龄：");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(ageJL);
		ageJTF=new JTextField();
		readerUpdPanel.add(ageJTF);

		phoneJL=new JLabel("电话：");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(phoneJL);
		phoneJTF=new JTextField();
		readerUpdPanel.add(phoneJTF);
		//JComboBox publishJCB = new JComboBox();
		//readerUpdPanel.add(publishJCB);

		deptJL=new JLabel("所在部门：");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(deptJL);
		deptJTF=new JTextField();
		readerUpdPanel.add(deptJTF);


		regJL=new JLabel("注册日期：");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerUpdPanel.add(regJL);
		regJTF=new JTextField();
		/*regtime = new JFormattedTextField(DateFormat.getDateInstance());
	    regtime.setValue(new java.util.Date());
	    regtime.addKeyListener(new DateListener());*/
		readerUpdPanel.add(regJTF);
		//登录取消按钮面板设计
		final JPanel buttonPanel=new JPanel();//修改按钮面板
		JButton modifyButton=new JButton("修改");
		modifyButton.addActionListener(new ReaderUpdActionListener());
		JButton closeButton=new JButton("关闭");
		closeButton.addActionListener(new CloseActionListener());

		buttonPanel.add(modifyButton);
		buttonPanel.add(closeButton);

		this.add(selectpanel,BorderLayout.NORTH);
		this.add(readerUpdPanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);

		this.setVisible(true);//设置窗体显示，否则不显示。
		setResizable(false);//取消最大化
	}
	//读者条件查询
	class SelectAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name = (String) conditionJCB.getSelectedItem();
			if (name.equals("读者编号")) {
				Gson gson = new Gson();
				List<ReaderEntity>readerEntityList = new ArrayList<ReaderEntity>();
				readerEntityList.add(gson.fromJson(ReaderDao.selectById(select_conditionJTF.getText().trim()), ReaderEntity.class));
				Object[][] results = getSelect(readerEntityList);
				jtable = new JTable(results, readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			} else if (name.equals("姓名")) {

				Object[][] results = getSelect(ReaderDao.selectByName(select_conditionJTF.getText().trim()));
				jtable = new JTable(results, readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}else if (name.equals("类型")) {

				Object[][] results = getSelect(ReaderDao.selectByType(Integer.parseInt(select_conditionJTF.getText().trim())));
				jtable = new JTable(results, readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}else if (name.equals("系部")) {

				Object[][] results = getSelect(ReaderDao.selectByDept(select_conditionJTF.getText().trim()));
				jtable = new JTable(results, readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new TableListener());
			}
		}
	}
	class TableListener extends MouseAdapter {

		public void mouseClicked(final MouseEvent e) {

			int selRow = jtable.getSelectedRow();
			IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
			readertypeJCB.setSelectedItem(jtable.getValueAt(selRow, 1).toString().trim());
			readerNameJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
			ageJTF.setText(jtable.getValueAt(selRow, 3).toString().trim());
			if (jtable.getValueAt(selRow, 4).toString().trim().equals("男"))
				radioButton1.setSelected(true);
			else
				radioButton2.setSelected(true);
			phoneJTF.setText(jtable.getValueAt(selRow, 5).toString().trim());
			deptJTF.setText(jtable.getValueAt(selRow, 6).toString().trim());
			regJTF.setText(jtable.getValueAt(selRow, 7).toString().trim());

		}
	}

	class ReaderUpdActionListener implements ActionListener {
		    /*private final DefaultTableModel model;
	        ReaderUpdActionListener(DefaultTableModel model) {
	            this.model = model;
	        }*/

		public void actionPerformed(final ActionEvent e) {

			String id=IDJTF.getText().trim();
			String type=(String)readertypeJCB.getSelectedItem();
			String name=readerNameJTF.getText().trim();
			Integer age=Integer.parseInt(ageJTF.getText().trim());
			String sex = "男";
			if (!radioButton1.isSelected()) {
				sex = "女";

			}
			String phone=phoneJTF.getText().trim();
			String dept=deptJTF.getText().trim();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date regdate = null;
			try {
				regdate = sdf.parse(regJTF.getText().trim());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			int typeNum = 0;
			if(type.equals("学生")){
				typeNum = 1;
			}
			else {
				typeNum = 2;
			}
			int i = ReaderDao.update(id,typeNum,name,age,sex,phone,dept,regdate);
			System.out.println(i);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results = getSelect(ReaderDao.selectAll());
				//model.setDataVector(results, readersearch);
				//jtable.setModel(model);
				jtable = new JTable(results, readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			}
		}
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new ReaderSelectandModify();
	}
}

