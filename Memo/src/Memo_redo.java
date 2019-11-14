

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
	
	JTextPane[] textEditor = new JTextPane[100];
	int totalNumbTab = 1;
	
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
		
		mainFrame.add(menuBar, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	private void createTextMemo() {
		// Set layout and bounds for the TextPanel that contains text editing area
		textPanel.setLayout(new BorderLayout());
		textPanel.setBounds(168, 72, 605, 704);

		
		// Create the first TextPane
		textEditor[0] = new JTextPane();
		// Initialize the first tab with a TextPane
		textTabbedPane.addTab("New Tab", textEditor[0]);
		
		// "Add Tab" button for the tab pane, and add actionListener to the button
		textTabbedPane.addTab(null, null);
		textTabbedPane.setTabComponentAt(1, addTabButton);
		addTabButton.addActionListener(this);
		
		textPanel.add(textTabbedPane);
		
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
			// Create new TextPane
			textEditor[totalNumbTab] = new JTextPane();
			// Insert the new TextPane above to the latest tab
			textTabbedPane.insertTab("New tab", null, textEditor[totalNumbTab], null, textTabbedPane.getTabCount() - 1);
		}
		
	}
}
