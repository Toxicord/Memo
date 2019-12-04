

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class TestTextArea implements ActionListener{
	private JFrame mainFrame = new JFrame();
	
	//used for save recursion
	private boolean loopConf;
	
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
	
	private JComboBox fontComboBox = new JComboBox();
	private JComboBox highlightColor = new JComboBox();
	
	private JButton addTabButton = new JButton("Add new tab");
	ImageIcon bold = new ImageIcon("src/bold.jpg");
	private JButton boldButton = new JButton(bold);
	ImageIcon italic = new ImageIcon("src/italic.jpg");
	private JButton italicButton = new JButton(italic);
	ImageIcon under = new ImageIcon("src/under.jpg");
	private JButton underlineButton = new JButton(under);
	// all components for the tools bar end here.
	
	
	
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
		TestTextArea DemoMemo = new TestTextArea("Demo");
	}
	
	public TestTextArea(String title) {
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800,500);
		mainFrame.setLayout(new BorderLayout());
		
		createMenuBar();
		createTextPanel();
		createToolBar();
		createNorthPanel();
		
		createFontComboBox();
		
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
	private void createHighlightComboBox() {
    String[] colorOptions = { "GRAY", "YELLOW", "RED", "CYAN", "ORANGE", "PINK" };
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
		//this adds action to the save and open menuItems
		mnSave.addActionListener(this); 
		mnOpen.addActionListener(this);
	}
	
	//this is the main save method
	private void save(int tabCount) {
		if(tabCount==0) {
			saveOne(tabCount);
		}else if(tabCount>0) {
			int one=JOptionPane.showConfirmDialog(null,"Would you like to save only your currently selected file?","SaveCurrentOneChoiceWindow",JOptionPane.YES_NO_OPTION);
			if(one==JOptionPane.YES_OPTION) {//might have to redo this section do to repitition
				saveOne(tabCount);
			}else if(one==JOptionPane.NO_OPTION) {
				int all=JOptionPane.showConfirmDialog(null,"Would you like to save all of your files?","SaveAllChoiceWindow",JOptionPane.YES_NO_OPTION);//currently loops, due to an error with loopConf
				if (all == JOptionPane.YES_OPTION) {
					String signifier="";
					int loop = 0;
					while(loop!=1) {
						signifier = JOptionPane.showInputDialog("Please enter a unique identifier for your files. \n(ie: 10/11/12 or this weeks notes)\n(also, note all the files are saved in the 'documents' folder");
						if ((signifier != null) && (signifier.length() > 0)) {
							loop=1;
						}else {
							break;
						}
					}
					if(loop==1) {
						int i=0;
						String outString;
						FileWriter writer = null;
						PrintWriter printer = null;
						while(!(i>tabCount)) {
							try {
								int namingPurpose=i+1;
								String dirSTR=(new JFileChooser().getFileSystemView().getDefaultDirectory().toString())+"/"+signifier+" tab #"+namingPurpose+".txt";
								File saveAll = new File(dirSTR);
								outString=textPane[i].getText();
								writer = new FileWriter(saveAll);
								printer = new PrintWriter (writer);
								printer.println(outString);
								writer.close();
								i++;
							} catch (IOException e) {
								i=tabCount;
								e.printStackTrace();
							}
						}
					}
				}
			}
			//if exited, it does nothing
		}
	}
	//main save end
	
	//saves a single file method because it's better to do this than rewrite it
		private void saveOne(int tabCount) {
			String textsPane;
			if(textPane[textTabbedPane.getSelectedIndex()].getText()==null) {
				textsPane="";
			}else {
				textsPane=textPane[textTabbedPane.getSelectedIndex()].getText();
			}
			JFileChooser saveSel = new JFileChooser();//defaults to documents directory
			int saveConf = saveSel.showSaveDialog(mnSave);
	        if (saveConf == JFileChooser.APPROVE_OPTION) {
	        	 File selFile = saveSel.getSelectedFile();
	        	 if (!selFile.getName().toLowerCase().endsWith(".txt")) {
	                	selFile = new File(selFile.getParentFile(), selFile.getName() + ".txt");
	                }
	        	 try {
					FileWriter fWriter = new FileWriter (selFile);
					PrintWriter pWriter = new PrintWriter (fWriter);
					pWriter.println(textsPane);
					pWriter.close();
	        	 }catch(FileNotFoundException e) {
	        		 e.printStackTrace();
	        	 } catch (IOException e) {
	        		 e.printStackTrace();
	        	 }
	        }
		}
	//save recursion end
		
	//this is open
	private void openF() {
		boolean error;
		JFileChooser openChoose = new JFileChooser();
		int intCastOpenChoose=openChoose.showSaveDialog(null);
		if (intCastOpenChoose == JFileChooser.APPROVE_OPTION) {
			File selFile = openChoose.getSelectedFile();
			 if (!selFile.getName().toLowerCase().endsWith(".txt")) {
             	error=true;
             }
			 try {
					
	        	 }catch(FileNotFoundException e) {
	        		 e.printStackTrace();
	        	 } catch (IOException e) {
	        		 e.printStackTrace();
	        	 }
	        }
		}
		
	}
	//open end
	
	private void newTab() {
		// Create new TextPane
		textPane[totalNumbTab] = new JTextPane();
		// Insert the new TextPane above to the latest tab
		textTabbedPane.insertTab("New tab", null, textPane[totalNumbTab], null, textTabbedPane.getTabCount() - 1);
		totalNumbTab++;//used to increment the tab number. Otherwise it'll only stay in textPane[1]
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonClicked = e.getActionCommand();
		if (buttonClicked.equalsIgnoreCase("Add new tab")) {
			newTab();
		}else if(e.getSource()==mnOpen) {
			openF();
		}else if(e.getSource()==mnSave) {
			save(totalNumbTab-1);
			}
		}
	}
	
	