

/*
 * This allows user to save file in Binary as well as .text
 * Methods include: save()
 * 
 */
import java.io.*;
import javax.swing.*;

public class SaveFile {

	private JMenuItem mnSave;
	
	public SaveFile(JMenuItem mnSave) {
		this.mnSave = mnSave;
	}
	
	/**
	 * Use this method to save a Memo file in binary or .txt
	 * @param textPane
	 */
	public void Save(JTextPane textPane) {
		
		String textsPane;
		//checks if the currently selected pane if it's empty and intializes it to be not null
		if(textPane.getText()==null) {
			textsPane="";
		}else {
			//gets text on current selected pane
			textsPane=textPane.getText();
		}
		
		JFileChooser saveDialog = new JFileChooser();
		int saveConf=saveDialog.showSaveDialog(mnSave);
		//prompts to save
		if(saveConf== JFileChooser.APPROVE_OPTION) {
			File selFile=saveDialog.getSelectedFile();
			//checks if selected file is in txt format
			if (selFile.getName().toLowerCase().endsWith(".txt")) {
	        	 try {
	        		 //write file to text
					 FileWriter fWriter = new FileWriter (selFile);
					 PrintWriter pWriter = new PrintWriter (fWriter);
					 pWriter.println(textsPane);
					 pWriter.close();
	        	 }catch(FileNotFoundException e1) {
	        		 //only occurs when saving file just before deleting
	        		 e1.printStackTrace();
	        	 } catch (IOException e2) {
	        		//should never occur
	        		 e2.printStackTrace();
	        	 }
			}else {
				try {
					//write object to object file
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(selFile));
					outputStream.writeObject(textPane);
					outputStream.close();
				} catch (FileNotFoundException ea) {
					System.out.println("File not found");
					//only occurs when saving file just before deleting
					ea.printStackTrace();
				} catch (IOException ee) {
					//if saved file is in an odd filetype
					ee.printStackTrace();
				}
			}
		};

	}
}
