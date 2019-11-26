
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Memo_redo implements ActionListener{
	private JFrame mainFrame = new JFrame();
	
	// all components for the menu bar are defined from here:
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnFile = new JMenu("File");
	private JMenu mnWindows = new JMenu("Windows");
	private JMenuItem mnSave = new JMenuItem("Save");
	private JMenuItem mnOpen = new JMenuItem("Open");
	// all components for the menu bar end here:

	
	private JPanel textPanel = new JPanel();
	private JPanel toolPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	
	private JTabbedPane textTabbedPane = new JTabbedPane();
	
	// An array of Text Pane
	private JTextPane[] textPane = new JTextPane[100];
	// Total number of tabs to keep track of the numbers of tabs
	private int totalNumbTab = 1;
	
	// all components for the tools bar are defined from here:
	private JToolBar toolBar = new JToolBar();
	
	private JComboBox<String> fontComboBox = new JComboBox<String>();
	private JComboBox<String> highlightColor = new JComboBox<String>();
	
	private JButton addTabButton = new JButton("Add new tab");
	ImageIcon bold = new ImageIcon("src/test/bold.jpg");
	private JButton boldButton = new JButton(bold);
	ImageIcon italic = new ImageIcon("src/test/italic.jpg");
	private JButton italicButton = new JButton(italic);
	ImageIcon under = new ImageIcon("src/test/under.jpg");
	private JButton underlineButton = new JButton(under);
	// all components for the tools bar end here.
	
	
	private String[] colorOptions = { "GRAY", "YELLOW", "RED", "CYAN", "ORANGE", "PINK" };
	private Highlighter.HighlightPainter grayPainter;
	private Highlighter.HighlightPainter yellowPainter;
	private Highlighter.HighlightPainter redPainter;
	private Highlighter.HighlightPainter cyanPainter;
	private Highlighter.HighlightPainter orangePainter;
	private Highlighter.HighlightPainter pinkPainter;
	private int firstUpdateIndex;
	private int counter;

	private Map<Integer, Highlighter.Highlight> highlights;
	
	public static void main(String[] args) {
		Memo_redo DemoMemo = new Memo_redo("Demo");
	}
	
	public Memo_redo(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700,700);
		mainFrame.setLayout(new BorderLayout());
		
		createMenuBar();
		createTextPanel();
		createToolBar();
		createNorthPanel();
		
		createFontComboBox();
		
		createHighlightComboBox();
		TextHighlight();
		actionToHighlightComboBox();
		
		mainFrame.add(northPanel, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	
	private void createNorthPanel() {
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(menuBar);
		northPanel.add(toolPanel);		
	}

	private void createFontComboBox() {
		String[] fontList = {"Arial", "Times New Roman", "Courier New", "Arial Black"};
		for (int i = 0; i < fontList.length; i++) {
			fontComboBox.addItem(fontList[i]);
		}
	}
	
	
	/*
	 * Color Highlight starts here
	 */
	private void createHighlightComboBox() {
    
		for (int i = 0; i < colorOptions.length; i++) {
			highlightColor.addItem(colorOptions[i]);
		}
	}	
	
	private void TextHighlight() {
		grayPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);
		yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
		orangePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
		pinkPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
		firstUpdateIndex = -1;
		counter = 0;
	}
	
	private void actionToHighlightComboBox() {
		highlightColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				text = textPane[1].getSelectedText();
				if (text != null && text.length() > 0) {
					int startIndex = textPane[1].getText().indexOf(text);
					int endIndex = startIndex + text.length();
					Highlighter highlighter = textPane[1].getHighlighter();
					
					String color = (String) highlightColor.getSelectedItem();
					try {
						if (color == colorOptions[0]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, grayPainter);
						} else if (color == colorOptions[1]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, yellowPainter);
						} else if (color == colorOptions[2]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, redPainter);
						} else if (color == colorOptions[3]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, cyanPainter);
						} else if (color == colorOptions[4]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, orangePainter);
						} else if (color == colorOptions[5]) {
							System.out.println("Color Selected : " + color);
							highlighter.addHighlight(startIndex, endIndex, pinkPainter);
						}
						Highlighter.Highlight[] highlightIndex = highlighter.getHighlights();

					} catch (BadLocationException ble) {
						ble.printStackTrace();
					}
				}	
				
			};
		});
		
		
	}
	
	
	/*
	 * Color Highlight ends here
	 */
	
	
	private void createToolBar() {
		toolPanel.setLayout(new BorderLayout());
		
		// Set layout and other features for the tool bar
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.setRollover(true);
		toolBar.addSeparator();
		
		// Add tools to the tool bar
		toolBar.add(fontComboBox);
		boldButton.setLayout(new GridLayout(1,1));
		toolBar.add(boldButton);
		toolBar.add(italicButton);
		toolBar.add(underlineButton);
		toolBar.add(highlightColor);
		
		// Add tool bar to the toolPanel
		toolPanel.add(toolBar);
	}
	

	private void createTextPanel() {
		// Set layout and bounds for the TextPanel that contains text editing area
		textPanel.setLayout(new BorderLayout());
		textPanel.setBounds(168, 72, 605, 704);

		
		// Create the first TextPane
		textPane[0] = new JTextPane();
		// Initialize the first tab with a TextPane
		textTabbedPane.addTab("New Tab", textPane[0]);
		
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
			textPane[totalNumbTab] = new JTextPane();
			// Insert the new TextPane above to the latest tab
			textTabbedPane.insertTab("New tab", null, textPane[totalNumbTab], null, textTabbedPane.getTabCount() - 1);
			totalNumbTab++;
		}
		
	}
}
