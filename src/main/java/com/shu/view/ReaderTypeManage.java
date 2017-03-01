//package com.shu.view;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.List;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//
//import com.bbm.db.ReaderTypeDao;
//import com.bbm.model.Reader;
//import com.bbm.model.ReaderType;
//import com.bbm.view.ReaderSelectandModify.SelectAction;
//import com.bbm.view.ReaderSelectandModify.TableListener;
//
//
//
//public class ReaderTypeManage extends JFrame {
//
//    private JLabel typeJL,idJL,nameJL,numJL,limitJL;
//    private JTextField typeJTF,idJTF,nameJTF,numJTF,limitJTF;
//    private JTable table;
//    private JScrollPane scrollPane;
//
//    private String[] readertype = { "读者类型编号", "读者类型名称", "可借图书数量", "可借图书期限" };
//    //private DefaultComboBoxModel ReaderTypeModel;
//    private DefaultTableModel model;
//
//    private Object[][] getSelect(List<ReaderType> list) {
//        Object[][] results = new Object[list.size()][readertype.length];
//        for (int i = 0; i < list.size(); i++) {
//            ReaderType ReaderType = list.get(i);
//            results[i][0] = ReaderType.getId();
//            results[i][1] = ReaderType.getName();
//            results[i][2] = ReaderType.getMaxborrownum();
//            results[i][3] = ReaderType.getLimit();
//        }
//        return results;
//    }
//
//    /**
//     * Create the frame
//     */
//    public ReaderTypeManage() {
//        super();
//        setTitle("读者类型管理");
//        setBounds(100, 100, 500, 350);
//
//        final JPanel selectPanel = new JPanel();
//        selectPanel.setLayout(new BorderLayout());
//
//        //查询条件面板
//        final JPanel selectPanel_condition = new JPanel();
//        typeJL=new JLabel("读者类型");
//        selectPanel_condition.add(typeJL);
//        typeJTF = new JTextField();
//        typeJTF.setColumns(20);
//        selectPanel_condition.add(typeJTF);
//        final JButton button_select = new JButton();
//        button_select.setText("查询");
//        button_select.addActionListener(new SelectAction());
//        selectPanel_condition.add(button_select);
//        selectPanel.add(selectPanel_condition, BorderLayout.NORTH);
//
//        //查询结果面板
//        final JPanel selectPanel_result = new JPanel();
//        scrollPane = new JScrollPane();
//        final DefaultTableModel model = new DefaultTableModel();
//        Object[][] results = getSelect(ReaderTypeDao.selectReaderType());
//        table = new JTable(results, readertype);
//
//        scrollPane.setViewportView(table);
//        //数据显示随表格大小变化
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.addMouseListener(new TableListener());
//
//        //显示边框
//        scrollPane.setPreferredSize(new Dimension(400, 150));
//        //不显示边框
//        //scrollPane_1.setSize(400,300);
//        selectPanel_result.add(scrollPane);
//        selectPanel.add(selectPanel_result,BorderLayout.CENTER);
//
//        //读者信息修改面板设计
//        final JPanel updatePanel = new JPanel();
//        final GridLayout gridLayout = new GridLayout(2, 4);
//        gridLayout.setVgap(10);
//        gridLayout.setHgap(10);
//        updatePanel.setLayout(gridLayout);
//        getContentPane().add(updatePanel);
//
//        idJL=new JLabel("读者类型编号：");
//        idJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(idJL);
//        idJTF=new JTextField();
//        updatePanel.add(idJTF);
//
//        nameJL=new JLabel("读者类型名称：");
//        nameJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(nameJL);
//        nameJTF=new JTextField();
//        updatePanel.add(nameJTF);
//
//        numJL=new JLabel("可借图书数量：");
//        numJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(numJL);
//        numJTF=new JTextField();
//        updatePanel.add(numJTF);
//
//        limitJL=new JLabel("可借图书期限：");
//        limitJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(limitJL);
//        limitJTF=new JTextField();
//        updatePanel.add(limitJTF);
//        //
//        final JPanel buttonPanel = new JPanel();
//        final JButton insertbt = new JButton();
//        insertbt.setText("添加");
//        insertbt.addActionListener(new InsertBtListener());
//        buttonPanel.add(insertbt);
//
//
//        final JButton updbt = new JButton();
//        updbt.setText("修改");
//        updbt.addActionListener(new UpdateBtListener());
//        buttonPanel.add(updbt);
//
//        final JButton delbt = new JButton();
//        delbt.setText("删除");
//        delbt.addActionListener(new DeleteBtListener());
//        buttonPanel.add(delbt);
//
//        final JButton exitbt = new JButton();
//        exitbt.setText("退出");
//        exitbt.addActionListener(new CloseActionListener());
//        buttonPanel.add(exitbt);
//
//        this.add(selectPanel,BorderLayout.NORTH);
//        this.add(updatePanel,BorderLayout.CENTER);
//        this.add(buttonPanel,BorderLayout.SOUTH);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序
//        this.setVisible(true);//设置窗体显示，否则不显示。
//        setResizable(false);//取消最大化
//        setVisible(true);
//        //
//
//    }
//    class SelectAction implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Object[][] results = getSelect(ReaderTypeDao.selectReaderType(typeJTF.getText().trim()));
//            table = new JTable(results, readertype);
//            scrollPane.setViewportView(table);
//            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//            table.addMouseListener(new TableListener());
//
//        }
//    }
//    class InsertBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            String name = nameJTF.getText().trim();
//            Integer num = Integer.parseInt(numJTF.getText().trim());
//            Integer limit = Integer.parseInt(limitJTF.getText().trim());
//            int i = ReaderTypeDao.insertReaderType(id,name,num,limit);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "添加成功");
//                //更新表中数据
//                new ReaderTypeManage();
//            }
//        }
//
//    }
//    //点击表中的某行，将此行数据显示到对应的文本框中
//    class TableListener extends MouseAdapter {
//
//        public void mouseClicked(final MouseEvent e) {
//            int selRow = table.getSelectedRow();
//            idJTF.setText(table.getValueAt(selRow, 0).toString().trim());
//            idJTF.setEditable(false);
//            nameJTF.setText(table.getValueAt(selRow, 1).toString().trim());
//            numJTF.setText(table.getValueAt(selRow, 2).toString().trim());
//            limitJTF.setText(table.getValueAt(selRow, 3).toString().trim());
//        }
//    }
//
//    class UpdateBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            String name = nameJTF.getText().trim();
//            Integer num = Integer.parseInt(numJTF.getText().trim());
//            Integer limit = Integer.parseInt(limitJTF.getText().trim());
//            int i = ReaderTypeDao.updateReaderType(id,name,num,limit);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "修改成功");
//                //更新表中数据
//                new ReaderTypeManage();
//            }
//        }
//    }
//    class DeleteBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            int i = ReaderTypeDao.deleteReaderType(id);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "删除成功");
//                //更新表中数据
//                new ReaderTypeManage();
//            }
//        }
//    }
//
//    class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
//        @Override
//        public void actionPerformed(final ActionEvent e) {
//            setVisible(false);
//        }
//    }
//    public static void main(String[] args) {
//        new ReaderTypeManage();
//    }
//}
