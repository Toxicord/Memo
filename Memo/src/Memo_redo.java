
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;


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
	private int totalNumbTab = 0;
	
	// all components for the tools bar are defined from here:
	private JToolBar toolBar = new JToolBar();
	
	private JComboBox<String> highlightColor = new JComboBox<String>();
	private JComboBox<String> fontComboBox = new JComboBox<String>();
	private String[] colorOptions = { "GRAY", "YELLOW", "RED", "CYAN", "ORANGE", "PINK" };
	private String[] fontOption = {"Arial", "Times New Roman", "Courier New", "Arial Black", "Courier", "Helvetica"};
	
	private JButton addTabButton = new JButton("Add new tab");
	private JButton deleteTabButton = new JButton("Delete tab");

	ImageIcon bold = new ImageIcon("bold.jpg");
	private JButton boldButton = new JButton(bold);
	ImageIcon italic = new ImageIcon("italic.jpg");
	private JButton italicButton = new JButton(italic);
	ImageIcon under = new ImageIcon("under.jpg");
	private JButton underlineButton = new JButton(under);
	
	// all components for the tools bar end here.
	

	
	public static void main(String[] args) {
		Memo_redo DemoMemo = new Memo_redo("Demo");
	}
	
	public Memo_redo(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700,700);
		mainFrame.setLayout(new BorderLayout());
		
		//textPane[textTabbedPane.getSelectedIndex()].addCaretListener(new SelectedText());
		
		createMenuBar();
		createTextPanel();
		createToolBar();
		createNorthPanel();
		
		createFontComboBox();
		actionToFontComboBox();
		
		createHighlightComboBox();
		actionToHighlightComboBox();
		
		
		
		mainFrame.add(northPanel, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	
	private void actionToFontComboBox() {
		fontComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				text = textPane[textTabbedPane.getSelectedIndex()].getSelectedText();
				if (text != null && text.length() > 0) {
					int startIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
					int endIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();

					StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
					String font = (String) fontComboBox.getSelectedItem();
					Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
					if (font == fontOption[0]) {
						System.out.println("Font Selected : " + font);
						StyleConstants.setFontFamily(style, "Arial");
					} else if (font == fontOption[1]) {
						System.out.println("Color Selected : " + font);
						StyleConstants.setFontFamily(style, "Times New Roman");
					} else if (font == fontOption[2]) {
						System.out.println("Color Selected : " + font);
						StyleConstants.setFontFamily(style, "Courier New");
					} else if (font == fontOption[3]) {
						System.out.println("Color Selected : " + font);
						StyleConstants.setFontFamily(style, "Arial Black");
					} else if (font == fontOption[4]) {
						System.out.println("Color Selected : " + font);
						StyleConstants.setFontFamily(style, "Courier");
					} else if (font == fontOption[5]) {
						System.out.println("Color Selected : " + font);
						StyleConstants.setFontFamily(style, "Helvetica");
					}
					//Highlighter.Highlight[] highlightIndex = highlighter.getHighlights();
					
			        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
			        doc.setCharacterAttributes(startIndex, endIndex - startIndex, style, false);

				}	
				
			};
		});		
	}
	//font action ends here

	
	private void createNorthPanel() {
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(menuBar);
		northPanel.add(toolPanel);		
	}

	private void createFontComboBox() {
		for (int i = 0; i < fontOption.length; i++) {
			fontComboBox.addItem(fontOption[i]);
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
	

	
	private void actionToHighlightComboBox() {
		highlightColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				text = textPane[textTabbedPane.getSelectedIndex()].getSelectedText();
				if (text != null && text.length() > 0) {
					int startIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
					int endIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();
					
					StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
					String color = (String) highlightColor.getSelectedItem();
					Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
					if (color == colorOptions[0]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.GRAY);
					} else if (color == colorOptions[1]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.YELLOW);
					} else if (color == colorOptions[2]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.RED);
					} else if (color == colorOptions[3]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.CYAN);
					} else if (color == colorOptions[4]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.ORANGE);
					} else if (color == colorOptions[5]) {
						System.out.println("Color Selected : " + color);
						StyleConstants.setBackground(style, Color.PINK);
					}
					//Highlighter.Highlight[] highlightIndex = highlighter.getHighlights();
					
			        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
			        doc.setCharacterAttributes(startIndex, endIndex - startIndex, style, false);

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
		toolBar.add(deleteTabButton);
		
		// Add actionlistener to all the buttons
		deleteTabButton.addActionListener(this);
		boldButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("bold button pressed");
		    	
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		        int start = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
		        int end = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();
		        if (start == end) { // No selection, cursor position.
		            return;
		        }
		        if (start > end) { // Backwards selection?
		            int life = start;
		            start = end;
		            end = life;
		        }
		        Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
		        StyleConstants.setBold(style, true);
		        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
		        doc.setCharacterAttributes(start, end - start, style, false);

		    }
		    
		});
		
		italicButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("italicize button pressed");
		    	
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		        int start = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
		        int end = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();
		        if (start == end) { // No selection, cursor position.
		            return;
		        }
		        if (start > end) { // Backwards selection?
		            int life = start;
		            start = end;
		            end = life;
		        }
		        Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
		        StyleConstants.setItalic(style, true);
		        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
		        doc.setCharacterAttributes(start, end - start, style, false);
		    }
		    
		});
		
		underlineButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("Underline button pressed");
		    	
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		        int start = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
		        int end = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();
		        if (start == end) { // No selection, cursor position.
		            return;
		        }
		        if (start > end) { // Backwards selection?
		            int life = start;
		            start = end;
		            end = life;
		        }
		        Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
		        StyleConstants.setUnderline(style, true);
		        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
		        doc.setCharacterAttributes(start, end - start, style, false);
		    }
		    
		});
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
		textTabbedPane.addTab("New Tab " + 1, textPane[0]);
		
		// "Add Tab" button for the tab pane, and add actionListener to the button
		textTabbedPane.addTab(null, null);
		textTabbedPane.setTabComponentAt(1, addTabButton);
		addTabButton.addActionListener(this);
		
		textPanel.add(textTabbedPane);
		
	}

	
	
	
	private void createMenuBar() {
		mnFile.add(mnSave);
		mnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				actionToSave();
			}
			
		});
		mnFile.add(mnOpen);
		mnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				actionToOpen();
			}
			
		});
		menuBar.add(mnFile);
		menuBar.add(mnWindows);
	}
	
	private void actionToSave() {
		JFileChooser saveDialog = new JFileChooser();
		int saveConf=saveDialog.showSaveDialog(mnSave);
		if(saveConf== JFileChooser.APPROVE_OPTION) {
			File selFile=saveDialog.getSelectedFile();
			
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(selFile));
				outputStream.writeObject(textPane[textTabbedPane.getSelectedIndex()]);
				outputStream.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void actionToOpen() {
		JFileChooser saveDialog = new JFileChooser();
		int saveConf=saveDialog.showOpenDialog(mnOpen);
		if(saveConf== JFileChooser.APPROVE_OPTION) {
			File selFile=saveDialog.getSelectedFile();
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selFile));
				JTextPane openTextPane = (JTextPane) inputStream.readObject();
				textTabbedPane.insertTab("New tab " + textTabbedPane.getTabCount(), null, openTextPane, null, textTabbedPane.getTabCount() - 1);
				inputStream.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found");
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonClicked = e.getActionCommand();
		
		if (buttonClicked.equalsIgnoreCase("Add new tab")) {
			// Create new TextPane
			textPane[textTabbedPane.getTabCount()-1] = new JTextPane();
			// Insert the new TextPane above to the latest tab
			textTabbedPane.insertTab("New tab " + textTabbedPane.getTabCount(), null, textPane[textTabbedPane.getTabCount()-1], null, textTabbedPane.getTabCount() - 1);
			
		} else if (buttonClicked.equalsIgnoreCase("Delete tab")) {
			if (textTabbedPane.getSelectedIndex() != textTabbedPane.getTabCount())
				textTabbedPane.remove(textTabbedPane.getSelectedIndex());
			totalNumbTab--;
		} 

		
	}
}
