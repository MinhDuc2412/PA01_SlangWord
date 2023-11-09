import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Searchsw extends JFrame implements ActionListener {
    JButton btnBack, ok;
    JTextField jIDf;
    SlangwordManagement slangWord = SlangwordManagement.getInstance();
    JTable j;
    JProgressBar jpb;

    public Searchsw() throws Exception {

        Container container = this.getContentPane();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel();
        titleLabel.setText("List of Slang Words");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        topPanel.add(titleLabel);

        JPanel jPaneltitle = new JPanel();
        jPaneltitle.setSize((new Dimension(50, 50)));
        jPaneltitle.setLayout(new BoxLayout(jPaneltitle, BoxLayout.LINE_AXIS));
        
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
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
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
        j.setRowSelectionAllowed(false);
        j.setEnabled(false);
        JScrollPane sp = new JScrollPane(j);
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnBack.setBackground(Color.white);
        btnBack.setForeground(new Color(0));
        jMid.add(sp);
        JPanel jend = new JPanel();
        jend.add(btnBack);


        container.setLayout(new BorderLayout());
        container.add(topPanel, BorderLayout.PAGE_START);
        container.add(jMid, BorderLayout.CENTER);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.EAST);
        container.add(jend, BorderLayout.PAGE_END);

        this.setTitle("Search by Slang Word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            Search.GUI();
        } else if (e.getSource().equals(ok)) {
          ChildThread childThread=new ChildThread(jpb);
        }
    }
    class ChildThread extends Thread {
        JProgressBar jProgressBar;
        ChildThread() {

            super("Child Thread");
            System.out.println("Child thread: " + this);
        }
        ChildThread(JProgressBar num){
            jProgressBar=num;
            jProgressBar.setValue(0);
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
        }
    }

    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Searchsw frame = new Searchsw();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
