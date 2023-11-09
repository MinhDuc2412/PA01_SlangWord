import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileWriter;

public class viewHistory extends JFrame implements ActionListener{
	JButton btnBack;
	JLabel jlbClearHistory;

    public viewHistory() throws Exception {
		JFrame temp=this;
		Container container = this.getContentPane();
		SlangwordManagement slangWord = SlangwordManagement.getInstance();

		JPanel topPanel=new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		JLabel titleLabel = new JLabel();
		titleLabel.setText("History Searched list");
		titleLabel.setForeground(Color.black);
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		topPanel.add(titleLabel);

		JPanel jPaneltitle=new JPanel();
		jPaneltitle.setSize((new Dimension(50, 50)));
		jPaneltitle.setLayout(new BoxLayout(jPaneltitle, BoxLayout.LINE_AXIS));
		jPaneltitle.add(titleLabel);

		JPanel jpEnd=new JPanel();
		jpEnd.setLayout(new BoxLayout(jpEnd, BoxLayout.PAGE_AXIS));
		jpEnd.add(Box.createRigidArea(new Dimension(0, 30)));
        jpEnd.add(jPaneltitle);
        jpEnd.add(Box.createRigidArea(new Dimension(0, 5)));
		topPanel.add(jpEnd);

		JPanel jMid=new JPanel();
		jMid.setLayout(new BoxLayout(jMid, BoxLayout.Y_AXIS));
		String[] columnNames = { "Slang Word", "Meaning", "Data Time" };
		String[][] historyTable=slangWord.readFileHistory(SlangwordManagement.FILE_SLANGWORD_HISTORY);
		JTable j=new JTable(historyTable, columnNames);
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
		jMid.add(sp);
		JLabel jlb=new JLabel();
		jlb.setAlignmentX(CENTER_ALIGNMENT);
		jlb.setText("You searched "+ slangWord.HistorySize+" words");
		jlb.setAlignmentX(CENTER_ALIGNMENT);
		jMid.add(Box.createRigidArea(new Dimension(0, 5)));
		jMid.add(jlb);
		jMid.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel bottomPanel = new JPanel();
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setFocusable(false);
		btnBack.setAlignmentX(CENTER_ALIGNMENT);
		btnBack.setBackground(Color.white);
		btnBack.setForeground(Color.BLACK);
		bottomPanel.add(btnBack);
		bottomPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        
        container.setLayout(new BorderLayout());
		container.add(topPanel,BorderLayout.PAGE_START);
		container.add(jMid, BorderLayout.CENTER);
		container.add(bottomPanel,BorderLayout.PAGE_END);
		
		this.setTitle("History");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(670, 680);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			this.dispose();
			new menu();
		}
	}


	public static void GUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewHistory frame = new viewHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}