import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Highlight extends Font{
	public void actionToHighlightComboBox(JTabbedPane textTabbedPane,JComboBox<String> highlightColor, String[] colorOptions,JTextPane[] textPane) {
		//adds a listener for highlighter
		highlightColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				//gets current text
				text = textPane[textTabbedPane.getSelectedIndex()].getSelectedText();
				if (text != null && text.length() > 0) {
					//gets hgihlight starts & ends from mouse highlighted text
					int startIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
					int endIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();
					
					StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
					//sets highlight str
					String color = (String) highlightColor.getSelectedItem();
					Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
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
					
			        style = textPane[textTabbedPane.getSelectedIndex()].getStyle("MyHilite");
			        doc.setCharacterAttributes(startIndex, endIndex - startIndex, style, false);

				}	
				
			};
		});
		
		
	}
}

