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



public class UpdatePWD extends JFrame{
	private JLabel nameJL,oldpwdJL,newpwd1JL,newpwd2JL;
	private JTextField nameJTF;
	private JPasswordField oldpwdJPF,newpwd1JPF,newpwd2JPF;
	private JButton confirmBt,cancelBt;
	private UsersEntity user = Login.getUser();
	public UpdatePWD(){
		super();
		setTitle("修改密码");
		setBounds(200, 200, 300, 200);

		final JPanel Panel = new JPanel();
		final GridLayout gridLayout = new GridLayout(4, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		Panel.setLayout(gridLayout);

		nameJL=new JLabel("用户名：");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJTF=new JTextField(20);
		nameJTF.setText(user.getName());
		nameJTF.setEditable(false);


		oldpwdJL=new JLabel("原密码：");
		oldpwdJL.setHorizontalAlignment(SwingConstants.CENTER);
		oldpwdJPF=new JPasswordField();

		newpwd1JL=new JLabel("新密码：");
		newpwd1JL.setHorizontalAlignment(SwingConstants.CENTER);
		newpwd1JPF=new JPasswordField();

		newpwd2JL=new JLabel("确认新密码：");
		newpwd2JL.setHorizontalAlignment(SwingConstants.CENTER);
		newpwd2JPF=new JPasswordField();

		Panel.add(nameJL);
		Panel.add(nameJTF);
		Panel.add(oldpwdJL);
		Panel.add(oldpwdJPF);
		Panel.add(newpwd1JL);
		Panel.add(newpwd1JPF);
		Panel.add(newpwd2JL);
		Panel.add(newpwd2JPF);

		final JPanel buttonPanel = new JPanel();
		final JButton confirmBt = new JButton();
		confirmBt.setText("确认");
		confirmBt.addActionListener(new confirmBtListener());
		buttonPanel.add(confirmBt);

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

	class confirmBtListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			Integer id = user.getId();
			String pwd = newpwd2JPF.getText().trim();
			int i = UserDao.updateUserPWD(id,pwd);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				//更新表中数据
				UpdatePWD.this.setVisible(false);
			}
		}
	}
}
