import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class menu  extends JFrame implements ActionListener {
    JButton btnView,btnSearch,btnHistory,btnAdd,btnEdit,btnDelete,btnReset,
    btnRandom,btnQuiz;
    SlangWord slangWord= SlangWord.getInstance();
    menu(){
        stylepanel spn=new stylepanel();
        Container container1 = this.getContentPane();
        Container container=new Container();
        Dimension size = new Dimension(160, 25);
        //Top Pannel
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel();
        titleLabel.setText("MENU");
        titleLabel.setForeground(new Color(222, 52, 49));
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        topPanel.add(titleLabel);

		JPanel jPaneltitle=new JPanel();
		jPaneltitle.setSize((new Dimension(50, 50)));
		jPaneltitle.setLayout(new BoxLayout(jPaneltitle, BoxLayout.LINE_AXIS));
		jPaneltitle.add(titleLabel);
        jPaneltitle.setBackground(new Color(247, 245, 245));
        jPaneltitle.setAlignmentX(CENTER_ALIGNMENT);

		JPanel jpEnd=new JPanel();
		jpEnd.setLayout(new BoxLayout(jpEnd, BoxLayout.PAGE_AXIS));
        jpEnd.add(jPaneltitle);
        jpEnd.add(Box.createRigidArea(new Dimension(0, 5)));
        jpEnd.setBackground(new Color(147, 176, 194));
        jpEnd.setAlignmentX(CENTER_ALIGNMENT);
		topPanel.add(jpEnd);
        topPanel.setAlignmentX(CENTER_ALIGNMENT);

        btnView=new JButton("View List");
        btnView.setAlignmentX(CENTER_ALIGNMENT);
        btnView.addActionListener(this);
        btnView.setFocusable(false);
        btnView.setBackground(new Color(245, 66, 87));
        btnView.setForeground(Color.white);
        btnView.setUI(new stylebutton());
        btnView.setMaximumSize(size);
        btnView.setPreferredSize(size);
        btnView.setMaximumSize(size);

        btnSearch=new JButton("Search");
        btnSearch.setAlignmentX(CENTER_ALIGNMENT);
        btnSearch.addActionListener(this);
        btnSearch.setFocusable(false);
        btnSearch.setBackground(new Color(245, 66, 87));
        btnSearch.setForeground(Color.white);
        btnSearch.setUI(new stylebutton());
        btnSearch.setMaximumSize(size);
        btnSearch.setPreferredSize(size);
        btnSearch.setMaximumSize(size);

        btnHistory=new JButton("Search History");
        btnHistory.setAlignmentX(CENTER_ALIGNMENT);
        btnHistory.addActionListener(this);
        btnHistory.setFocusable(false);
        btnHistory.setBackground(new Color(245, 66, 87));
        btnHistory.setForeground(Color.white);
        btnHistory.setUI(new stylebutton());
        btnHistory.setMaximumSize(size);
        btnHistory.setPreferredSize(size);
        btnHistory.setMaximumSize(size);

        btnAdd=new JButton("Add");
        btnAdd.setAlignmentX(CENTER_ALIGNMENT);
        btnAdd.addActionListener(this);
        btnAdd.setFocusable(false);
        btnAdd.setBackground(new Color(245, 66, 87));
        btnAdd.setForeground(Color.white);
        btnAdd.setUI(new stylebutton());
        btnAdd.setMaximumSize(size);
        btnAdd.setPreferredSize(size);
        btnAdd.setMaximumSize(size);

        btnDelete=new JButton("Delete");
        btnDelete.setAlignmentX(CENTER_ALIGNMENT);
        btnDelete.addActionListener(this);
        btnDelete.setFocusable(false);
        btnDelete.setBackground(new Color(245, 66, 87));
        btnDelete.setForeground(Color.white);
        btnDelete.setUI(new stylebutton());
        btnDelete.setMaximumSize(size);
        btnDelete.setPreferredSize(size);
        btnDelete.setMaximumSize(size);

        btnEdit=new JButton("Edit");
        btnEdit.setAlignmentX(CENTER_ALIGNMENT);
        btnEdit.addActionListener(this);
        btnEdit.setFocusable(false);
        btnEdit.setBackground(new Color(245, 66, 87));
        btnEdit.setForeground(Color.white);
        btnEdit.setUI(new stylebutton());
        btnEdit.setMaximumSize(size);
        btnEdit.setPreferredSize(size);
        btnEdit.setMaximumSize(size);

        btnReset=new JButton("Reset");
        btnReset.setAlignmentX(CENTER_ALIGNMENT);
        btnReset.addActionListener(this);
        btnReset.setFocusable(false);
        btnReset.setBackground(new Color(245, 66, 87));
        btnReset.setForeground(Color.white);
        btnReset.setUI(new stylebutton());
        btnReset.setMaximumSize(size);
        btnReset.setPreferredSize(size);
        btnReset.setMaximumSize(size);

        btnRandom=new JButton("Random");
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);
        btnRandom.addActionListener(this);
        btnRandom.setFocusable(false);
        btnRandom.setBackground(new Color(245, 66, 87));
        btnRandom.setForeground(Color.white);
        btnRandom.setUI(new stylebutton());
        btnRandom.setMaximumSize(size);
        btnRandom.setPreferredSize(size);
        btnRandom.setMaximumSize(size);

        btnQuiz=new JButton("Quiz");
        btnQuiz.setAlignmentX(CENTER_ALIGNMENT);
        btnQuiz.addActionListener(this);
        btnQuiz.setFocusable(false);
        btnQuiz.setBackground(new Color(245, 66, 87));
        btnQuiz.setForeground(Color.white);
        btnQuiz.setUI(new stylebutton());
        btnQuiz.setMaximumSize(size);
        btnQuiz.setPreferredSize(size);
        btnQuiz.setMaximumSize(size);



        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(topPanel);

        container.add((Box.createRigidArea(new Dimension(0,20))));
        container.add(btnView);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnSearch);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnHistory);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnAdd);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnDelete);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnEdit);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnReset);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnRandom);
        container.add((Box.createRigidArea(new Dimension(0,10))));
        container.add(btnQuiz);

        
        spn.setLayout(new BorderLayout());
        spn.add(container,BorderLayout.CENTER);
        container1.add(spn);
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.setVisible(true);
		this.setSize(450, 450);
        this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==btnView){
            this.dispose();
            try {
                listword.GUI();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource()==btnSearch){
            this.dispose();
            try {
                Search.GUI();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource()==btnHistory){
            this.dispose();
            try {
                viewHistory.GUI();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource()==btnAdd){
            this.dispose();
            try {
                new AddSW();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource()==btnDelete){
            this.dispose();
            try {
                new deleteslangword();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource()==btnReset){
            try {
                slangWord.Reset();
                JOptionPane.showMessageDialog(this,"Reset successfully");  
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        }
        else if (e.getSource().equals(btnRandom)){
            this.dispose();
            Random.GUI();
        }else if (e.getSource().equals(btnEdit)){
            this.dispose();
            Edit.GUI();
        }else if (e.getSource().equals(btnQuiz)){
            this.dispose();
            Quiz.GUI();
        }
    }

    public static void GUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu frame = new menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args){
        menu.GUI();
    }
    
}
