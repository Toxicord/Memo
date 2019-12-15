

/*
 * This allows user to open a selected Memo file and display the Memo to the tab pane
 * Methods include: Open()
 */
import java.io.*;
import java.util.Scanner;
import javax.swing.*;


public class OpenFile {

	private JMenuItem mnOpen;
	private JTextPane[] textPaneArray;
	
	public OpenFile(JTextPane[] textPaneArray, JMenuItem mnOpen) {
		this.textPaneArray = textPaneArray;
		this.mnOpen = mnOpen;
	}
	
	/**
	 * Use this to open a Memo File.
	 * @param textTabbedPane
	 */
	public void Open(JTabbedPane textTabbedPane) {
		JFileChooser openDialog = new JFileChooser();
		int saveConf= openDialog.showOpenDialog(mnOpen);
		if(saveConf== JFileChooser.APPROVE_OPTION) {
			File selFile= openDialog.getSelectedFile();
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selFile));
				JTextPane openTextPane = (JTextPane) inputStream.readObject();
				textPaneArray[textTabbedPane.getTabCount()-1] = openTextPane;
				textTabbedPane.insertTab(selFile.getName(), null, textPaneArray[textTabbedPane.getTabCount()-1], null, textTabbedPane.getTabCount() - 1);
				inputStream.close();
				//log
            	System.out.print("\n(B)File successfully opened and converted from binary to string on a new pane!");
			} catch (FileNotFoundException e1) {
				System.out.println("File not found");
				e1.printStackTrace();
			} catch (IOException e2) {
				if (selFile.getName().toLowerCase().endsWith(".txt")) {
					try {
						String tabContent = null;
						Scanner scnr = new Scanner(selFile);
						scnr.useDelimiter("\\Z"); 
						while(scnr.hasNextLine()){
							tabContent = scnr.next();
						}
						textPaneArray[textTabbedPane.getTabCount()-1] = new JTextPane();
					    //sets the tab content to file content
						textPaneArray[textTabbedPane.getTabCount()-1].setText(tabContent);
						textTabbedPane.insertTab(selFile.getName(), null, textPaneArray[textTabbedPane.getTabCount()-1], null, textTabbedPane.getTabCount() - 1);
						//log
	                	System.out.print("\n(T)File successfully opened and converted from text to string on a new pane!");
	                	scnr.close();
					    }catch(FileNotFoundException e3) {
					    	System.out.println("File not found");
					    }
                }else {
                	//log
                	System.out.print("\nUNSUPPORTED filetype was selected to open");
                }
			} catch (ClassNotFoundException e5) {
				System.out.println("Class not found");
				
			}
		}
	}
}
