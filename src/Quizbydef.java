import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class Quizbydef extends JFrame implements ActionListener {

    //
    JButton back, next, answerA, answerB, answerC, answerD;
    JLabel titSW;
    SlangwordManagement slangWord = SlangwordManagement.getInstance();
    int check = 0;
    String[] quizChoice;
    String answer;
    int correct=0;
    int total=1;

    public Quizbydef() throws Exception {
        Container container = this.getContentPane();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Choose Slang word");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
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
        titSW = new JLabel();
        titSW.setFont(new Font("Times New Roman", Font.BOLD, 28));
        titSW.setAlignmentX(CENTER_ALIGNMENT);
        jpEnd.add(titSW);
        jpEnd.add(Box.createRigidArea(new Dimension(0, 5)));
        topPanel.add(jpEnd);
        JPanel toppanel = new JPanel();
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.LINE_AXIS));
        JPanel pp = new JPanel();
        pp.setLayout(new BoxLayout(pp, BoxLayout.PAGE_AXIS));
        pp.add(toppanel);
        JPanel toppanel1 = new JPanel();
        JPanel pp1 = new JPanel();
        pp1.setLayout(new BoxLayout(pp1, BoxLayout.PAGE_AXIS));
        pp1.add(Box.createRigidArea(new Dimension(0, 30)));
        pp1.add(toppanel1);

        String[] randWord = slangWord.RandomSlangWord();
        back = new JButton("Back");
        back.setAlignmentX(CENTER_ALIGNMENT);
        back.addActionListener(this);
        back.setFocusable(false);
        back.setBackground(Color.white);
        back.setForeground(Color.black);
        back.setUI(new designButton());
        back.setPreferredSize(new Dimension(90, 50));

        next = new JButton("Next");
        next.setAlignmentX(CENTER_ALIGNMENT);
        next.addActionListener(this);
        next.setFocusable(false);
        next.setBackground(Color.white);
        next.setForeground(Color.black);
        next.setUI(new designButton());
        next.setPreferredSize(new Dimension(90, 50));

        answerA = new JButton();
        answerA.setAlignmentX(CENTER_ALIGNMENT);
        answerA.addActionListener(this);
        answerA.setFocusable(false);
        answerA.setBackground(Color.white);
        answerA.setForeground(Color.black);
        answerA.setUI(new designButton());
        answerA.setPreferredSize(new Dimension(75, 30));

        answerB = new JButton();
        answerB.setAlignmentX(CENTER_ALIGNMENT);
        answerB.addActionListener(this);
        answerB.setFocusable(false);
        answerB.setBackground(Color.white);
        answerB.setForeground(Color.black);
        answerB.setUI(new designButton());
        answerB.setPreferredSize(new Dimension(75, 30));

        answerC = new JButton();
        answerC.setAlignmentX(CENTER_ALIGNMENT);
        answerC.addActionListener(this);
        answerC.setFocusable(false);
        answerC.setBackground(Color.white);
        answerC.setForeground(Color.black);
        answerC.setUI(new designButton());
        answerC.setPreferredSize(new Dimension(75, 30));

        answerD = new JButton();
        answerD.setAlignmentX(CENTER_ALIGNMENT);
        answerD.addActionListener(this);
        answerD.setFocusable(false);
        answerD.setBackground(Color.white);
        answerD.setForeground(Color.black);
        answerD.setUI(new designButton());
        answerD.setPreferredSize(new Dimension(75, 30));
        
        GridLayout layout = new GridLayout(0, 2);
        layout.setHgap(40);
        layout.setVgap(40);
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(answerA);
        panel.add(answerB);
        panel.add(answerC);
        panel.add(answerD);

        JPanel jPanel = new JPanel();
        jPanel.add(panel);
        jPanel.setAlignmentX(CENTER_ALIGNMENT);
        pp1.add(jPanel);
        container.setLayout(new BorderLayout());

        JPanel ppp = new JPanel();
        ppp.setLayout(new BoxLayout(ppp, BoxLayout.PAGE_AXIS));
        ppp.add(pp);
        ppp.add(pp1);
        container.add(topPanel, BorderLayout.PAGE_START);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.LINE_START);
        container.add(panel, BorderLayout.CENTER);
        container.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.LINE_END);
        JPanel jEndEnd = new JPanel();
        jEndEnd.setLayout(new FlowLayout(FlowLayout.CENTER));
        jEndEnd.add(back);
        jEndEnd.add(next);
        container.add(jEndEnd, BorderLayout.PAGE_END);

        quizChoice = slangWord.randomMultipleChoice(2);
        answerA.setText(quizChoice[1]);
        answerB.setText(quizChoice[2]);
        answerC.setText(quizChoice[3]);
        answerD.setText(quizChoice[4]);
        titSW.setText(quizChoice[0]);
        answer = quizChoice[5];
        if (answerA.getText().equals(answer)){
            check = 1;
        }
        if (answerB.getText().equals(answer)) {
            check = 2;
        }
        if (answerC.getText().equals(answer)) {
            check = 3;
        }
        if (answerD.getText().equals(answer)) {
            check = 4;
        }
        answerA.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        answerB.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        answerC.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        answerD.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        
        this.setTitle("Quiz by definition");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(900, 550);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


   
    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Quizbydef frame = new Quizbydef();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            JOptionPane.showMessageDialog(null,
                    "Result: "+correct+"/"+total);
            this.dispose();
            Quiz.GUI();
        } else if (e.getSource().equals(next)) {
            total++;
            answerA.setEnabled(true);
            answerB.setEnabled(true);
            answerC.setEnabled(true);
            answerD.setEnabled(true);
            answerA.setBackground(Color.white);
            answerB.setBackground(Color.white);
            answerC.setBackground(Color.white);
            answerD.setBackground(Color.white);
            answerA.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            answerB.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            answerC.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            answerD.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            quizChoice = slangWord.randomMultipleChoice(2);
            answerA.setText(quizChoice[1]);
            answerB.setText(quizChoice[2]);
            answerC.setText(quizChoice[3]);
            answerD.setText(quizChoice[4]);
            titSW.setText(quizChoice[0]);
            answer = quizChoice[5];
            if (answerA.getText().equals(answer)){
                check = 1;
            }
            if (answerB.getText().equals(answer)) {
                check = 2;
            }
            if (answerC.getText().equals(answer)) {
                check = 3;
            }
            if (answerD.getText().equals(answer)) {
                check = 4;
            }
        } else if (e.getSource().equals(answerA)) {
            if (check == 1) {
                JOptionPane.showMessageDialog(null, "Correct Answer");
                correct++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer", "Alert", JOptionPane.WARNING_MESSAGE);
               
            }
            answerA.setEnabled(false);
            answerB.setEnabled(false);
            answerC.setEnabled(false);
            answerD.setEnabled(false);
        } else if (e.getSource().equals(answerB)) {
            if (check == 2) {
                JOptionPane.showMessageDialog(null, "Correct Answer");
                correct++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer", "Alert", JOptionPane.WARNING_MESSAGE);
                
            }
            answerA.setEnabled(false);
            answerB.setEnabled(false);
            answerC.setEnabled(false);
            answerD.setEnabled(false);
        } else if (e.getSource().equals(answerC)) {
            if (check == 3) {
                JOptionPane.showMessageDialog(null, "Correct Answer");
                correct++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer", "Alert", JOptionPane.WARNING_MESSAGE);
                
            }
            answerA.setEnabled(false);
            answerB.setEnabled(false);
            answerC.setEnabled(false);
            answerD.setEnabled(false);
        } else if (e.getSource().equals(answerD)) {
            if (check == 4) {
                JOptionPane.showMessageDialog(null, "Correct Answer");
                correct++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer", "Alert", JOptionPane.WARNING_MESSAGE);
                

            }

            answerA.setEnabled(false);
            answerB.setEnabled(false);
            answerC.setEnabled(false);
            answerD.setEnabled(false);
        }
    }
}

