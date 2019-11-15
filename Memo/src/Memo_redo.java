

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Memo_redo implements ActionListener{
	private JFrame mainFrame = new JFrame();
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnFile = new JMenu("File");
	private JMenu mnWindows = new JMenu("Windows");
	private JMenuItem mnSave = new JMenuItem("Save");
	private JMenuItem mnOpen = new JMenuItem("Open");
	
	private JPanel textPanel = new JPanel();
	private JPanel toolPanel = new JPanel();
	
	private JTabbedPane textTabbedPane = new JTabbedPane();
	
	private JTextPane[] textEditor = new JTextPane[100];
	// Total number of tabs to keep track of the numbers of tabs
	private int totalNumbTab = 1;
	
	private JButton addTabButton = new JButton("Add new tab");
	private JButton boldButton = new JButton("B");
	private JButton italicButton = new JButton("I");
	private JButton underlineButton = new JButton("U");
	
	
	private JToolBar sideToolBar = new JToolBar(JToolBar.VERTICAL);
	
	private JComboBox fontComboBox = new JComboBox();
	private JComboBox highlightColor = new JComboBox();
	
	
	public static void main(String[] args) {
		Memo_redo DemoMemo = new Memo_redo("Demo");
	}
	
	public Memo_redo(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400,200);
		mainFrame.setLayout(new BorderLayout());
		
		
		createMenuBar();
		createTextPanel();
		createToolBar();
		
		createFontComboBox();
		
		mainFrame.add(menuBar, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		mainFrame.add(toolPanel, BorderLayout.WEST);

		
		mainFrame.setVisible(true);
	}
	
	private void createFontComboBox() {
		
	}

	private void createToolBar() {
		toolPanel.setLayout(new BorderLayout());
		sideToolBar.setRollover(true);
		sideToolBar.addSeparator();
		sideToolBar.add(fontComboBox);
		sideToolBar.add(boldButton);
		sideToolBar.add(italicButton);
		sideToolBar.add(underlineButton);
		sideToolBar.add(highlightColor);
		
		toolPanel.add(sideToolBar);
	}
	

	private void createTextPanel() {
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
