//package com.shu.view;
//
//import java.awt.BorderLayout;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import com.bbm.view.BookTypeManage.CloseActionListener;
//import com.bbm.view.BookTypeManage.InsertBtListener;
//
//public class FineSet extends JFrame{
//	private JLabel fineJL,textJL;
//	private JTextField fineJTF;
//	public static double fine;
//
//	public FineSet(){
//		super();
//		setTitle("罚金设置");
//		setBounds(100, 100, 400, 100);
//
//		final JPanel Panel = new JPanel();
//		//Panel.setLayout(new BorderLayout());
//
//		fineJL=new JLabel("罚金设置：");
//		fineJTF=new JTextField(10);
//		textJL=new JLabel("元/天");
//
//		Panel.add(fineJL);
//		Panel.add(fineJTF);
//		Panel.add(textJL);
//
//		final JPanel buttonPanel = new JPanel();
//		final JButton setfinebt = new JButton();
//		setfinebt.setText("设置");
//		setfinebt.addActionListener(new SetfineBtListener());
//		buttonPanel.add(setfinebt);
//
//		final JButton exitbt = new JButton();
//		exitbt.setText("退出");
//		exitbt.addActionListener(new CloseActionListener());
//		buttonPanel.add(exitbt);
//
//		this.add(Panel,BorderLayout.CENTER);
//		this.add(buttonPanel,BorderLayout.SOUTH);
//
//		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序
//		this.setVisible(true);//设置窗体显示，否则不显示。
//		setResizable(false);//取消最大化
//
//	}
//	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			setVisible(false);
//		}
//	}
//	class SetfineBtListener implements ActionListener { // 添加关闭按钮的事件监听器
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			fine=Double.valueOf(fineJTF.getText().trim());
//			JOptionPane.showMessageDialog(null, "罚金标准设置成功");
//			setVisible(false);
//		}
//	}
//	/*public static void main(String[] args) {
//		new FineSet();
//	}*/
//}
