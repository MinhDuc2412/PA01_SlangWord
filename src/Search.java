import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JFrame implements ActionListener {
    JButton Searchbydef, Searchbysw, btnBack;
    Search(){
        Container con = this.getContentPane();
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.LINE_AXIS));
        Searchbydef=new JButton("Search by definition");
        Searchbydef.setAlignmentX(CENTER_ALIGNMENT);
        Searchbydef.addActionListener(this);
        Searchbydef.setFocusable(false);
        Searchbydef.setForeground(Color.black);
        Searchbydef.setUI(new designButton());
        Searchbysw=new JButton("Search by slang word");
        Searchbysw.setAlignmentX(CENTER_ALIGNMENT);
        Searchbysw.addActionListener(this);
        Searchbysw.setFocusable(false);
        Searchbysw.setForeground(Color.black);
        Searchbysw.setUI(new designButton());

        btnBack=new JButton("Back");
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setForeground(Color.black);
        btnBack.setUI(new designButton());
        jPanel.add(Searchbydef);
        jPanel.add(Box.createRigidArea(new Dimension(10,0)));
        jPanel.add(Searchbysw);
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.PAGE_AXIS));
        jPanel1.add(jPanel);
        jPanel1.add(Box.createRigidArea(new Dimension(0,15)));
        jPanel1.add(btnBack);
        // Setting con
        con.setLayout(new BorderLayout());
        con.add(Box.createRigidArea(new Dimension(0,15)),BorderLayout.PAGE_START);
        con.add(jPanel1,BorderLayout.CENTER);
        // Setting JFrame
        this.setTitle("Choose your choice");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(600,200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
 
    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Search frame = new Search();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Searchbydef)){
            this.dispose();
            Searchdef.GUI();
        }
        else if (e.getSource().equals(Searchbysw)){
            this.dispose();
            Searchsw.GUI();
        }
        else if (e.getSource().equals(btnBack)){
            this.dispose();
            menu.GUI();
        }
    }
}
