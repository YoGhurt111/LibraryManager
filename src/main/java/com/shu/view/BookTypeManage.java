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
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.table.DefaultTableModel;
//
//import com.bbm.db.BookTypeDao;
//import com.bbm.model.BookType;
//import com.bbm.model.ReaderType;
//import com.bbm.view.ReaderTypeManage.CloseActionListener;
//import com.bbm.view.ReaderTypeManage.DeleteBtListener;
//import com.bbm.view.ReaderTypeManage.InsertBtListener;
//import com.bbm.view.ReaderTypeManage.SelectAction;
//import com.bbm.view.ReaderTypeManage.TableListener;
//import com.bbm.view.ReaderTypeManage.UpdateBtListener;
//
//public class BookTypeManage extends JFrame{
//
//    private JLabel typeJL,idJL,nameJL;
//    private JTextField typeJTF,idJTF,nameJTF;
//    private JTable table;
//    private JScrollPane scrollPane;
//
//    private String[] booktype = { "图书类型编号", "图书类型名称"};
//
//    private DefaultTableModel model;
//
//    private Object[][] getSelect(List<BookType> list) {
//        Object[][] results = new Object[list.size()][booktype.length];
//        for (int i = 0; i < list.size(); i++) {
//            BookType bookType = list.get(i);
//            results[i][0] = bookType.gettypeid();
//            results[i][1] = bookType.gettypename();
//        }
//        return results;
//    }
//
//    /**
//     * Create the frame
//     */
//    public BookTypeManage() {
//        super();
//        setTitle("图书类型管理");
//        setBounds(100, 100, 400, 300);
//
//        final JPanel selectPanel = new JPanel();
//        selectPanel.setLayout(new BorderLayout());
//
//        //查询条件面板
//        final JPanel selectPanel_condition = new JPanel();
//        typeJL=new JLabel("图书类型");
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
//        Object[][] results = getSelect(BookTypeDao.selectBookType());
//        table = new JTable(results, booktype);
//
//        scrollPane.setViewportView(table);
//        //数据显示随表格大小变化
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.addMouseListener(new TableListener());
//
//        //显示边框
//        scrollPane.setPreferredSize(new Dimension(350, 100));
//        //不显示边框
//        //scrollPane_1.setSize(400,300);
//        selectPanel_result.add(scrollPane);
//        selectPanel.add(selectPanel_result,BorderLayout.CENTER);
//
//        //读者信息修改面板设计
//        final JPanel updatePanel = new JPanel();
//        final GridLayout gridLayout = new GridLayout(2, 2);
//        gridLayout.setVgap(10);
//        gridLayout.setHgap(10);
//        updatePanel.setLayout(gridLayout);
//        getContentPane().add(updatePanel);
//
//        idJL=new JLabel("图书类型编号：");
//        idJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(idJL);
//        idJTF=new JTextField();
//        updatePanel.add(idJTF);
//
//        nameJL=new JLabel("图书类型名称：");
//        nameJL.setHorizontalAlignment(SwingConstants.CENTER);
//        updatePanel.add(nameJL);
//        nameJTF=new JTextField();
//        updatePanel.add(nameJTF);
//
//        //按钮面板
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
//        this.setVisible(true);//设置窗体显示，否则不显示。
//        setResizable(false);//取消最大化
//        setVisible(true);
//        //
//
//    }
//    class SelectAction implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            String name = typeJTF.getText().trim();
//            Object[][] results = getSelect(BookTypeDao.selectBookType(name));
//            table = new JTable(results, booktype);
//            scrollPane.setViewportView(table);
//            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//            table.addMouseListener(new TableListener());
//        }
//    }
//    class InsertBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            String name = nameJTF.getText().trim();
//            int i = BookTypeDao.insertBookType(id,name);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "添加成功");
//                //更新表中数据
//                new BookTypeManage();
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
//        }
//    }
//
//    class UpdateBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            String name = nameJTF.getText().trim();
//            int i = BookTypeDao.updateBookType(id,name);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "修改成功");
//                //更新表中数据
//                new BookTypeManage();
//            }
//        }
//    }
//    class DeleteBtListener implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            Integer id = Integer.parseInt(idJTF.getText().trim());
//            int i = BookTypeDao.deleteBookType(id);
//            if (i == 1) {
//                JOptionPane.showMessageDialog(null, "删除成功");
//                //更新表中数据
//                new BookTypeManage();
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
//        new BookTypeManage();
//    }
//
//}
