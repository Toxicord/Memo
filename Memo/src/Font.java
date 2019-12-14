import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Font {
	public void actionToFontComboBox(JTabbedPane textTabbedPane,JComboBox<String> fontComboBox, String[] fontOption,JTextPane[] textPane) {
		//adds a listener for fonts
		fontComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				//gets current text
				text = textPane[textTabbedPane.getSelectedIndex()].getSelectedText();
				if (text != null && text.length() > 0) {
					//gets font starts & ends from highlighted text
					int startIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionStart();
					int endIndex = textPane[textTabbedPane.getSelectedIndex()].getSelectionEnd();

					StyledDocument doc = textPane[textTabbedPane.getSelectedIndex()].getStyledDocument();
					//sets font str
					String font = (String) fontComboBox.getSelectedItem();
					Style style = textPane[textTabbedPane.getSelectedIndex()].addStyle("MyHilite", null);
					//combobox option checker, sets fonts accordingly
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
}