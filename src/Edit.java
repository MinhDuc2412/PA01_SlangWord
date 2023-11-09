import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Edit extends JFrame implements ActionListener {
    JButton btnBack, ok, savechanged;
    JTextField jIDf;
    SlangwordManagement slangWord = SlangwordManagement.getInstance();
    JTable j;
    String oldValue="";
    JProgressBar jpb;
    int row = -1;
    int col = -1;
    int check=1;

    public Edit() throws Exception {

        Container container = this.getContentPane();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel();
        titleLabel.setText("List Slang Words");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Monaco", Font.PLAIN, 30));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        topPanel.add(titleLabel);

        JPanel jPaneltitle = new JPanel();
        jPaneltitle.setSize((new Dimension(50, 50)));
        jPaneltitle.setLayout(new BoxLayout(jPaneltitle, BoxLayout.LINE_AXIS));
        jPaneltitle.setBackground(new Color(222, 52, 49));
        jPaneltitle.add(titleLabel);

        JPanel jpEnd = new JPanel();
        jpEnd.setLayout(new BoxLayout(jpEnd, BoxLayout.PAGE_AXIS));
        jpEnd.add(Box.createRigidArea(new Dimension(0, 30)));
        jpEnd.add(jPaneltitle);
        jpEnd.add(Box.createRigidArea(new Dimension(0, 5)));
        topPanel.add(jpEnd);

        JLabel jlb = new JLabel();
        jlb.setAlignmentX(CENTER_ALIGNMENT);
        jlb.setText("Total: " + slangWord.getSize() + " words");
        topPanel.add(jlb);

        JPanel jInfor = new JPanel();
        JLabel jId = new JLabel("Slang Word");
        jIDf = new JTextField(10);
        jIDf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                ChildThread childThread=new ChildThread(jpb);
            }
        });
        ok = new JButton("OK");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener(this);
        jInfor.add(jId);
        jInfor.add(jIDf);
        jpb=new JProgressBar();
        jpb.setValue(0);
        jpb.setStringPainted(true);
        jInfor.add(jpb);
        topPanel.add(jInfor);

        JPanel jMid = new JPanel();
        jMid.setLayout(new BoxLayout(jMid, BoxLayout.PAGE_AXIS));
        String[] columnNames = {"STT", "Slang Word", "Meaning"};
        String[][] findSW = slangWord.getSW_Data();
        j = new JTable(findSW, columnNames);
        j.setAlignmentX(CENTER_ALIGNMENT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        j.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        j.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        j.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnBack.setBackground(Color.white);
        btnBack.setForeground(new Color(222, 52, 49));
        jMid.add(sp);
        JPanel jend = new JPanel();
        jend.add(btnBack);
        savechanged = new JButton("Save Change");
        savechanged.addActionListener(this);
        savechanged.setFocusable(false);
        savechanged.setAlignmentX(CENTER_ALIGNMENT);
        savechanged.setBackground(Color.white);
        savechanged.setForeground(new Color(208, 208, 27, 255));
        jend.add(savechanged);


        container.setLayout(new BorderLayout());
        container.add(topPanel, BorderLayout.PAGE_START);
        container.add(jMid, BorderLayout.CENTER);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.EAST);
        container.add(jend, BorderLayout.PAGE_END);
        
        this.setTitle("Random Slang Word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    class ChildThread extends Thread {
        JProgressBar jProgressBar;
        ChildThread() {

            super("Child Thread");
            System.out.println("Child thread: " + this);
            
        }
        ChildThread(JProgressBar num){
            jProgressBar=num;
            start();
        }
        public void run() {
            String id = jIDf.getText().toString();
            String[][] findDef = slangWord.findSlangWord(id);
            int ii=0;
            if (findDef!=null){
                for (int i=0;i<findDef.length;i++){
                    try {
                        slangWord.writeFileHistory(findDef[i][1], findDef[i][2]);
                        ii=100/(findDef.length-i);
                        jProgressBar.setValue(ii);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            String[] columnNames = {"STT", "Slang Word", "Meaning"};
            DefaultTableModel model = new DefaultTableModel(findDef, columnNames);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            j.setModel(model);
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            j.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            j.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            j.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            ListSelectionModel cellSelectionModel = j.getSelectionModel();
            cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int[] selectedRow = j.getSelectedRows();
                    String[] arrSelect=new String[2];
                    for (int i = 0; i < selectedRow.length; i++) {
                        arrSelect[0] = (String) j.getValueAt(selectedRow[i], 1);
                        arrSelect[1] = (String) j.getValueAt(selectedRow[i], 2);
                    }
                    if (arrSelect[0]!=null || arrSelect[1]!=null){
                        if (check!=0) {
                            int a = JOptionPane.showConfirmDialog(null,
                                    "Did you want this meaning: " + arrSelect[1] + "?");
                            String[][] findDef = new String[1][3];
                            findDef[0][0]="1";
                            findDef[0][1]=arrSelect[0];
                            findDef[0][2]=arrSelect[1];
                            oldValue=arrSelect[1];
                            String[] columnNames = {"STT", "Slang Word", "Meaning"};
                            DefaultTableModel model = new DefaultTableModel(findDef, columnNames);
                            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                            j.setModel(model);
                            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                            j.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                            j.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                            j.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                            ListSelectionModel cellSelectionModel = j.getSelectionModel();
                            cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            check=0;
                        }else{
                            j.getModel().addTableModelListener(new TableModelListener() {
                                @Override
                                public void tableChanged(TableModelEvent e) {
                                    row = j.getSelectedRow();
                                    col = j.getSelectedColumn();
                                    if (row == -1 || col == -1)
                                        return;
                                    String valueChanged = (String) j.getValueAt(row, col);
                                }
                            });
                        }

                    }}
            });
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            menu.GUI();
        } else if (e.getSource().equals(ok)) {
            String id = jIDf.getText().toString();
            String[][] findDef = slangWord.findSlangWord(id);
            String[] columnNames = {"STT", "Slang Word", "Meaning"};
            DefaultTableModel model = new DefaultTableModel(findDef, columnNames);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            j.setModel(model);
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            j.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            j.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            j.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            ListSelectionModel cellSelectionModel = j.getSelectionModel();
            cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int[] selectedRow = j.getSelectedRows();
                    String[] arrSelect=new String[2];
                    for (int i = 0; i < selectedRow.length; i++) {
                        arrSelect[0] = (String) j.getValueAt(selectedRow[i], 1);
                        arrSelect[1] = (String) j.getValueAt(selectedRow[i], 2);
                    }
                    if (arrSelect[0]!=null || arrSelect[1]!=null){
                    if (check!=0) {
                        int a = JOptionPane.showConfirmDialog(null,
                                "Did you want this meaning: " + arrSelect[1] + "?");
                        String[][] findDef = new String[1][3];
                        findDef[0][0]="1";
                        findDef[0][1]=arrSelect[0];
                        findDef[0][2]=arrSelect[1];
                        oldValue=arrSelect[1];
                        String[] columnNames = {"STT", "Slang Word", "Meaning"};
                        DefaultTableModel model = new DefaultTableModel(findDef, columnNames);
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        j.setModel(model);
                        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                        j.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        j.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        j.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        ListSelectionModel cellSelectionModel = j.getSelectionModel();
                        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        check=0;
                    }else{
                        j.getModel().addTableModelListener(new TableModelListener() {
                            @Override
                            public void tableChanged(TableModelEvent e) {
                                row = j.getSelectedRow();
                                col = j.getSelectedColumn();
                                if (row == -1 || col == -1)
                                    return;
                                String valueChanged = (String) j.getValueAt(row, col);
                            }
                        });
                    }

                }}
            });

        } else if (e.getSource().equals(savechanged)) {
            if (col == 2) {
                String[][] findDef = slangWord.findSlangWord((String) j.getValueAt(row, 1));
                for (int i=0;i<findDef.length;i++){
                    if (findDef[i][2].equals(oldValue)){
                        slangWord.UpdateSlangWord((String) j.getValueAt(row, 1), findDef[i][2], (String) j.getValueAt(row, 2));
                        JOptionPane.showMessageDialog(null, "Successfully Updated");
                        check=1;
                    }
                }
            }
        }
    }

    
    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Edit frame = new Edit();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
