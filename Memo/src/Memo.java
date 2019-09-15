import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Button;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class Memo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Memo window = new Memo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Memo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(168, 72, 605, 704);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 22, 585, 657);
		panel.add(tabbedPane);
		
		
		
		JTextPane textPane = new JTextPane();
		
		
		TextArea text = new TextArea();
        Font font = new Font("Serif", Font.ITALIC, 20);
        textPane.setFont(font);
        textPane.setForeground(Color.blue);
        textPane.add(text, BorderLayout.CENTER);

        tabbedPane.addTab("New tab", null, textPane, null);
		
		
		
		JButton btnNewButton_1 = new JButton("Add new tab");
		tabbedPane.addTab("+", null, btnNewButton_1, null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(43, 31, 740, 23);
		frame.getContentPane().add(toolBar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnWindows = new JMenu("Windows");
		menuBar.add(mnWindows);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextPane textPane1 = new JTextPane();
				tabbedPane.insertTab("New tab", null, textPane1, null, tabbedPane.getTabCount() - 1);
			}
		});
		
	}
}
