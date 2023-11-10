import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {
    JButton quizbydef, quizbydsw,btnBack;
    Quiz(){
        Container con = this.getContentPane();
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.LINE_AXIS));
        quizbydef=new JButton("Quiz by definition");
        quizbydef.setAlignmentX(CENTER_ALIGNMENT);
        quizbydef.addActionListener(this);
        quizbydef.setFocusable(false);
        quizbydef.setBackground(Color.white);
        quizbydef.setForeground(Color.black);
        quizbydef.setUI(new designButton());
        quizbydsw=new JButton("Quiz by slang word");
        quizbydsw.setAlignmentX(CENTER_ALIGNMENT);
        quizbydsw.addActionListener(this);
        quizbydsw.setFocusable(false);
        quizbydsw.setBackground(Color.white);
        quizbydsw.setForeground(Color.black);
        quizbydsw.setUI(new designButton());

        btnBack=new JButton("Back");
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setBackground(Color.white);
        btnBack.setForeground(Color.black);
        btnBack.setUI(new designButton());
        jPanel.add(quizbydef);
        jPanel.add(Box.createRigidArea(new Dimension(10,0)));
        jPanel.add(quizbydsw);
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.PAGE_AXIS));
        jPanel1.add(jPanel);
        jPanel1.add(Box.createRigidArea(new Dimension(0,15)));
        jPanel1.add(btnBack);
        jPanel1.add(Box.createRigidArea(new Dimension(0,30)));

        con.setLayout(new BorderLayout());
        con.add(Box.createRigidArea(new Dimension(0,15)),BorderLayout.PAGE_START);
        con.add(jPanel1,BorderLayout.CENTER);
        
        this.setTitle("Quiz");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(new Dimension(400,170));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    
    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Quiz frame = new Quiz();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnBack)){
            this.dispose();
            menu.GUI();
        }
        else if (e.getSource().equals(quizbydsw)){
            this.dispose();
            Quizbysw.GUI();
        }
        else if (e.getSource().equals(quizbydef)){
            this.dispose();
            Quizbydef.GUI();
        }
    }

}
