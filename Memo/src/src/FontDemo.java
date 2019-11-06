package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class FontDemo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FontDemo window = new FontDemo();
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
	public FontDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(53, 41, 335, 231);
		frame.getContentPane().add(textPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(53, 6, 76, 27);
		frame.getContentPane().add(comboBox);
		String[] fontList = {"Serif", "Times New Roman", "Arial"};
		for (int i = 0; i < fontList.length; i ++) {
			comboBox.addItem((String) fontList[i]);
		}
		
		
		
		
	}
}
