package com.shu.view;

import com.shu.dao.UserDao;
import com.shu.entity.UsersEntity;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class UserAdd extends JFrame{
	private JLabel nameJL,pwdJL;
	private JTextField nameJTF;
	private JPasswordField pwdJPF;
	private JButton addBt,cancelBt;

	public UserAdd(){
		super();
		setTitle("添加用户");
		setBounds(200, 200, 300, 150);

		final JPanel Panel = new JPanel();
		final GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		Panel.setLayout(gridLayout);

		nameJL=new JLabel("用户名：");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJTF=new JTextField(20);

		pwdJL=new JLabel("密码：");
		pwdJL.setHorizontalAlignment(SwingConstants.CENTER);
		pwdJPF=new JPasswordField();

		Panel.add(nameJL);
		Panel.add(nameJTF);
		Panel.add(pwdJL);
		Panel.add(pwdJPF);


		final JPanel buttonPanel = new JPanel();
		final JButton addBt = new JButton();
		addBt.setText("添加");
		addBt.addActionListener(new AddBtListener());
		buttonPanel.add(addBt);

		final JButton cancelBt = new JButton();
		cancelBt.setText("取消");
		cancelBt.addActionListener(new CloseActionListener());
		buttonPanel.add(cancelBt);

		this.add(Panel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);

		this.setVisible(true);//设置窗体显示，否则不显示。
		setResizable(false);//取消最大化
	}
	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器

		public void actionPerformed(final ActionEvent e) {
			setVisible(false);
		}
	}

	class AddBtListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
            UsersEntity usersEntity = new UsersEntity();
			usersEntity.setName(nameJTF.getText().trim());
			usersEntity.setPassword(pwdJPF.getText().trim());
			int i = UserDao.insert(usersEntity);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				//更新表中数据
				UserAdd.this.setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		new UserAdd();

	}

}
