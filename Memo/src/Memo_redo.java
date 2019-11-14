import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Memo_redo implements ActionListener{
	private JFrame mainFrame = new JFrame();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenu mnWindows = new JMenu("Windows");
	JMenuItem mnSave = new JMenuItem("Save");
	JMenuItem mnOpen = new JMenuItem("Open");
	
	JPanel textPanel = new JPanel();
	
	JTabbedPane textTabbedPane = new JTabbedPane();
	
	JEditorPane[] textEditor;
	int totalNumbTab = 0;
	
	JButton addTabButton = new JButton("Add new tab");
	
	public static void main(String[] args) {
		Memo_redo DemoMemo = new Memo_redo("Demo");
	}
	
	public Memo_redo(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400,200);
		mainFrame.setLayout(new BorderLayout());
		
		
		createMenuBar();
		createTextMemo();
		
		mainFrame.add(menuBar, BorderLayout.SOUTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	private void createTextMemo() {
		// "Add Tab" button for the tab pane
		textTabbedPane.setComponentAt(0, addTabButton);
		addTabButton.addActionListener(this);
		
	}

	private void createMenuBar() {
		mnFile.add(mnSave);
		mnFile.add(mnOpen);
		menuBar.add(mnFile);
		menuBar.add(mnWindows);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonClicked = e.getActionCommand();
		
		if (buttonClicked.equalsIgnoreCase("Add new tab")) {
			textTabbedPane.add(textEditor[totalNumbTab] = new JEditorPane());
		}
		
	}
}
