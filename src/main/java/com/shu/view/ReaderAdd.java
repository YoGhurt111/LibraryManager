package com.shu.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.shu.dao.ReaderDao;
import com.shu.dao.ReaderTypeDao;
import com.shu.entity.ReaderEntity;
import com.shu.entity.ReadertypeEntity;

public class ReaderAdd extends JFrame {
	private static final long serialVersionUID = 8425429108121704223L;
	private JPanel readerAddJP,sexJP,buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1,JRB2;//性别单选按钮
	private JLabel IDJL,categoryJL,readerNameJL,sexJL,phoneJL,deptJL,ageJL,regJL;
	private JTextField IDJTF,readerNameJTF,phoneJTF,deptJTF,ageJTF,regtimeJTF;
	private JComboBox readertypeJCB;	//类别选择组合框
	private JButton addJB,closeJB;

	class ReaderAddAction implements ActionListener{//添加按钮的事件监听器类
		private final JRadioButton button1;
		ReaderAddAction(JRadioButton button1) {//传入性别单选按钮
			this.button1 = button1;
		}
		public void actionPerformed(final ActionEvent e) {
			if (IDJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者编号不可以为空");
				return;
			}
			if (IDJTF.getText().length() != 8) {
				JOptionPane.showMessageDialog(null, "读者编号位数为8位");
				return;
			}
			if (readerNameJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者姓名不可以为空");
				return;
			}

			ReaderEntity reader = new ReaderEntity();
			reader.setReaderid(IDJTF.getText());

			String readertype=(String) readertypeJCB.getSelectedItem();
            int num_readertype;
            if(readertype == "学生"){
                num_readertype = 1;
            }
            else
            {
                num_readertype = 2;
            }
            reader.setType(num_readertype);
			reader.setName(readerNameJTF.getText());
			reader.setAge(Integer.parseInt(ageJTF.getText()));
			String sex = "女";
			if (button1.isSelected()) {
				sex = "男";
			}
			reader.setSex(sex);
			reader.setPhone(phoneJTF.getText());
			reader.setDept(deptJTF.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date regdate = null;
            try {
                regdate = sdf.parse(regtimeJTF.getText());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            reader.setRegdate(regdate);
            int i = ReaderDao.insert(reader);
			if (i == 1) {//添加成功
				JOptionPane.showMessageDialog(null, "添加成功");
				ReaderAdd.this.setVisible(false);//关闭该窗口
			}
		}
	}

	class CloseAction implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}


	public ReaderAdd(){
		setBounds(200, 200, 500, 200);
		setTitle("读者信息添加");
		//登录取消按钮面板
		buttonJP=new JPanel();
		//读者信息添加面板设计
		readerAddJP = new JPanel();
		readerAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);//指定行列间距
		gridLayout.setHgap(10);
		readerAddJP.setLayout(gridLayout);
		this.add(readerAddJP);

		IDJL=new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(IDJL);
		IDJTF=new JTextField();
		readerAddJP.add(IDJTF);
		readerNameJL=new JLabel("姓名：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(readerNameJL);
		readerNameJTF=new JTextField();
		readerAddJP.add(readerNameJTF);
		categoryJL=new JLabel("类别：");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(categoryJL);
		//下拉列表
		readertypeJCB = new JComboBox();
		//readertypeModel = (DefaultComboBoxModel) readertypeJCB.getModel();
		// 从数据库中取出图书类别
		List<ReadertypeEntity> list = ReaderTypeDao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			ReadertypeEntity rt = list.get(i);  //以ReaderType类对象保存查询返回的读者类别的记录
			readertypeJCB.addItem(rt.getTypename());
		}
		readerAddJP.add(readertypeJCB);
		sexJL=new JLabel("性别：");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(sexJL);
		sexJP = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);
		readerAddJP.add(sexJP);
		JRB1 = new JRadioButton();//性别选择单选框
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB1.setText("男");
		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
		JRB2.setText("女");
		ageJL=new JLabel("年龄：");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(ageJL);
		ageJTF=new JTextField();
		readerAddJP.add(ageJTF);
		phoneJL=new JLabel("电话：");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(phoneJL);
		phoneJTF=new JTextField();
		readerAddJP.add(phoneJTF);
		deptJL=new JLabel("所在部门：");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(deptJL);
		deptJTF=new JTextField();
		readerAddJP.add(deptJTF);
		regJL=new JLabel("注册日期：");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(regJL);
		regtimeJTF = new JTextField();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//Date类设置日期格式
		String str = format.format(new java.util.Date());//获取系统当前时间
		regtimeJTF.setText(str);
		regtimeJTF.setEditable(false);
		readerAddJP.add(regtimeJTF);

		//登录取消按钮面板设计
		addJB=new JButton("添加");
		addJB.addActionListener(new ReaderAddAction(JRB1));
		closeJB=new JButton("关闭");
		closeJB.addActionListener(new CloseAction());
		buttonJP.add(addJB);
		buttonJP.add(closeJB);
		this.add(readerAddJP,BorderLayout.CENTER);
		this.add(buttonJP,BorderLayout.SOUTH);

		this.setVisible(true);
		setResizable(false);//取消最大化
	}

	public static void main(String[] args) {
		new ReaderAdd();
	}
}
