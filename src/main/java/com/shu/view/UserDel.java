package com.shu.view;

import com.shu.dao.UserDao;
import com.shu.entity.UsersEntity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class UserDel extends JFrame{

    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private String[] userCol = { "用户编号", "用户名", "密码"};

    private Object[][] getSelect(List<UsersEntity> list) {
        Object[][] results = new Object[list.size()][userCol.length];
        for (int i = 0; i < list.size(); i++) {
            UsersEntity user = list.get(i);
            results[i][0] = user.getId();
            results[i][1] = user.getName();
            results[i][2] = user.getPassword();
        }
        return results;
    }
    public UserDel() {
        super();
        setTitle("删除用户");
        setBounds(100, 100, 500, 250);
        //查询结果面板
        final JPanel dataPanel = new JPanel();
        scrollPane = new JScrollPane();
        model = new DefaultTableModel();
        Object[][] results = getSelect(UserDao.selectAll());
        table = new JTable(results, userCol);

        scrollPane.setViewportView(table);
        //数据显示随表格大小变化
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //显示边框
        scrollPane.setPreferredSize(new Dimension(400, 150));
        //不显示边框
        //scrollPane_1.setSize(400,300);
        dataPanel.add(scrollPane);

        //按钮面板
        final JPanel buttonPanel = new JPanel();
        final JButton delbt = new JButton();
        delbt.setText("删除");
        delbt.addActionListener(new DeleteBtListener());
        buttonPanel.add(delbt);

        final JButton exitbt = new JButton();
        exitbt.setText("退出");
        exitbt.addActionListener(new CloseActionListener());
        buttonPanel.add(exitbt);

        this.add(dataPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);

        this.setVisible(true);//设置窗体显示，否则不显示。
        setResizable(false);//取消最大化

    }
    class DeleteBtListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            int selRow = table.getSelectedRow();
            Integer id = Integer.parseInt(table.getValueAt(selRow, 0).toString().trim());
            int i = UserDao.deleteById(id);
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "删除成功");
                //更新表中数据
                new UserDel();
                setVisible(false);
            }
        }
    }

    class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
        public void actionPerformed(final ActionEvent e) {
            setVisible(false);
        }
    }


}
