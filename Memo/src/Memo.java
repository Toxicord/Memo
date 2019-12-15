
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.*;

import ok.OpenFile;
import ok.SaveFile;



public class Memo implements ActionListener{
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
	private String[] colorOptions = { "GRAY", "YELLOW", "RED", "CYAN", "ORANGE", "PINK", "NONE" };
	private String[] fontOption = {"Arial", "Times New Roman", "Courier New", "Arial Black", "Courier", "Helvetica"};
	
	private JButton addTabButton = new JButton("Add new tab");
	private JButton deleteTabButton = new JButton("Delete tab");
	private JButton renameButton = new JButton("Rename tab");

	ImageIcon bold = new ImageIcon("bold.jpg");
	private JButton boldButton = new JButton(bold);
	ImageIcon italic = new ImageIcon("italic.jpg");
	private JButton italicButton = new JButton(italic);
	ImageIcon under = new ImageIcon("under.jpg");
	private JButton underlineButton = new JButton(under);
	// all components for the tools bar end here.
	
	private Highlighter newHighlighter = new Highlighter(colorOptions);
	private Font newFontChanger = new Font(fontOption);
	private SaveFile newSaveFile = new SaveFile(mnSave);
	private OpenFile newOpenFile = new OpenFile(textPane,mnOpen);
	
	public Memo(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700,700);
		mainFrame.setLayout(new BorderLayout());
		
		//textPane[textTabbedPane.getSelectedIndex()].addCaretListener(new SelectedText());
		
		//methods for creating buttons and menus and the like
		createMenuBar();
		createTextPanel();
		createNorthPanel();
		createToolBar();
		createFontComboBox();
		createHighlightComboBox();
		//actions for those buttons, jcomboboxes and alike
		actionToFontComboBox();
		actionToHighlightComboBox();
		actionToBoldBtn();
		actionToItalicBtn();
		actionToUnderLineBtn();
		actionToSave();
		actionToOpen();
		actionToRename();
		
		//adds the frames, northPanel being for menu items and buttons & the text is enough said
		mainFrame.add(northPanel, BorderLayout.NORTH);
		mainFrame.add(textPanel, BorderLayout.CENTER);
		
		mainFrame.setVisible(true);
	}
	
	private void actionToHighlightComboBox() {
		highlightColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newHighlighter.Highlight(textPane[textTabbedPane.getSelectedIndex()],(String) highlightColor.getSelectedItem());
					//combobox option checker, sets fonts accordingly
				
			};
		});				
	}

	private void actionToFontComboBox() {
		fontComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newFontChanger.ChangeFont(textPane[textTabbedPane.getSelectedIndex()],(String) fontComboBox.getSelectedItem());
					//combobox option checker, sets fonts accordingly
				
			};
		});				
	}

	private void createMenuBar() {
		//adds components to proper menus
		mnFile.add(mnSave);
		mnFile.add(mnOpen);
		menuBar.add(mnFile);
		menuBar.add(mnWindows);
	}
	
	private void createTextPanel() {
		// Set layout and bounds for the TextPanel that contains text editing area
		textPanel.setLayout(new BorderLayout());
		textPanel.setBounds(168, 72, 605, 704);

		
		// Create the first TextPane
		textPane[0] = new JTextPane();
		//sets info about the program
		textPane[0].setText("This program currently supports:\n\tSaving & opening our Binary filetypes (saves and opens by default):\n\t\t that save your text & non-text related data(like highlights and font stuff) to a file.\n\tSaving & opening .TXT formats: \n\t\tYou can open .txt formats natively, and you can save your notes as text by\n\t\tputting .txt at the end of your file when choosing the files save name.\n\t\t(Note that choosing the .txt format will NOT save your highlight or font data)");
		totalNumbTab++;
		// Initialize the first tab with a TextPane with a name of the array+1
		textTabbedPane.addTab("Tab " + 1, textPane[0]);
		
		
		// "Add Tab" button for the tab pane, and add actionListener to the button
		textTabbedPane.addTab(null, null);
		textTabbedPane.setTabComponentAt(1, addTabButton);
		addTabButton.addActionListener(this);
		
		//adds the first tab to the textPanel
		textPanel.add(textTabbedPane);
	}
	
	private void createNorthPanel() {
		//adds menu components in gridlayout to the northPanel
		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(menuBar);
		northPanel.add(toolPanel);		
	}

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
		toolBar.add(renameButton);
		
		deleteTabButton.addActionListener(this);
		
		// Add tool bar to the toolPanel
		toolPanel.add(toolBar);
	}
	
	private void createFontComboBox() {
		//future proofing if different font options are implemented
		for (int i = 0; i < fontOption.length; i++) {
			fontComboBox.addItem(fontOption[i]);
		}
	}
	
	private void createHighlightComboBox() {
		//future proofing if different color options are implemented
		for (int i = 0; i < colorOptions.length; i++) {
			highlightColor.addItem(colorOptions[i]);
		}
	}	
	
	private void actionToBoldBtn() {
		boldButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("bold button pressed");
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		    	//gets starts and ends
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
	}
	
	private void actionToItalicBtn() {
		italicButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("italicize button pressed");
		    	
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		    	//gets starts and ends
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
				
	}

	private void actionToUnderLineBtn() {
		underlineButton.addActionListener( new ActionListener() {

		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("Underline button pressed");
		    	
		    	StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
		    	//gets starts and ends
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
	}

	private void actionToSave() {
		//note, save all was working for text files in older versions, but I've already implemented it 3 times and I'm refusing to retool it due to structuring differeneces. No hard feelings, we've all messed up a bit here and there.
		mnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newSaveFile.save(textPane[textTabbedPane.getSelectedIndex()]);
			}
		});
			
	}
	
	private void actionToOpen() {
		mnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newOpenFile.Open(textTabbedPane);
			}
		});
	}
	
	private void actionToRename() {
		renameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//promts user for a new name
				String message = JOptionPane.showInputDialog("Enter Tab Name:");
				if(message==null){
					//log
				   System.out.println("\nThe rename button was canceled.");
				   return;
				}else if(message.isEmpty()) {
					int newTabnum=(totalNumbTab+1);
					message=("Tab "+newTabnum);
				}
				//renames current selected tab to message;
				textTabbedPane.setTitleAt(textTabbedPane.getSelectedIndex(), message);
				//log
				System.out.println("\nThe tab: "+textTabbedPane.getSelectedIndex()+" was renamed.");
			}
			
		});
	}
	
	private void addATab() {
		String message = JOptionPane.showInputDialog("Enter Tab Name:");
		if(message==null){
			//log
		   System.out.println("\nThe add tab button was canceled.");
		   return;
		}else if(message.isEmpty()) {
			int newTabnum=(totalNumbTab+1);
			message=("Tab "+newTabnum);
		}
		// Create new TextPane
		textPane[totalNumbTab] = new JTextPane();
		// Insert the new TextPane above to the latest tab
		textTabbedPane.insertTab(message, null, textPane[totalNumbTab], null, textTabbedPane.getTabCount() - 1);
		totalNumbTab++;//used to increment the tab number. Otherwise it'll only stay in textPane[1]
		//log
		System.out.println("Add tab button was clicked. Total number of tab is: " + totalNumbTab);
	}
	
	private void delTab() {
		//gets the tab number
		int indexOfDeletedTab = textTabbedPane.getSelectedIndex();
		if (textTabbedPane.getTabCount() > 1) {
			//prompts user for delete
			int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this tab?");
			//if yes
			if (selection == 0) {
				//removes tab from array
				textTabbedPane.remove(indexOfDeletedTab);
				for (int i = indexOfDeletedTab+1; i < textPane.length; i++) {
					textPane[i-1] = textPane[i];
					if(i < textTabbedPane.getTabCount()) {
						//used to adjust the current non-named tab counts
						textTabbedPane.setTitleAt(i-1, "Tab " + (i));
						//log
						System.out.println("Delete tab button was clicked. Total number of tab is: " + totalNumbTab);
					}
				}
				totalNumbTab--;
			}
			//else does nothing
		} 
		//log
		System.out.println("\nThe delete tab button was canceled.");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonClicked = e.getActionCommand();
		
		if (buttonClicked.equalsIgnoreCase("Add new tab")) {
			//calls method
			addATab();
			
		} else if (buttonClicked.equalsIgnoreCase("Delete tab")) {
			//calls method
			delTab();
			}
		}
	}
