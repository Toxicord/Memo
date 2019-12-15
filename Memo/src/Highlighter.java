import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Highlighter {
	private String[] colorOptions;

	public Highlighter(String[] colorOptions) {
		this.colorOptions = colorOptions;
	}
	
	public void Highlight(JTextPane textPane, String color) {

		String text = null;
		//gets current text
		text = textPane.getSelectedText();
		if (text != null && text.length() > 0) {
			//gets highlight starts & ends from mouse highlighted text
			int startIndex = textPane.getSelectionStart();
			int endIndex = textPane.getSelectionEnd();
			
			StyledDocument doc = textPane.getStyledDocument();
			Style style = textPane.addStyle("MyHilite", null);
			//combobox option checker, sets highlights accordingly
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
			} else if (color == colorOptions[6]) {
				System.out.println("Color Selected : " + color);
				StyleConstants.setBackground(style, Color.WHITE);
			} 
			
	        style = textPane.getStyle("MyHilite");
	        doc.setCharacterAttributes(startIndex, endIndex - startIndex, style, false);

		}	
		



}

