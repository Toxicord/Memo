/*
 * 	Font class
 * 	This create a tool to change font of a selected string
 * 	Methods include: ChangeFont() 
 */
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Font {
	private String[] fontOption;
	
	public Font(String[] fontOptions)  {
		this.setFontOption(fontOptions);
	}
	
	/**
	 * Use this method to change the font of a selected string
	 * @param textPane
	 * @param font
	 */
	public void ChangeFont(JTextPane textPane, String font) {
		String text = null;
		//gets current text
		text = textPane.getSelectedText();
		if (text != null && text.length() > 0) {
			//gets font starts & ends from highlighted text
			int startIndex = textPane.getSelectionStart();
			int endIndex = textPane.getSelectionEnd();

			StyledDocument doc = textPane.getStyledDocument();
			//sets font str
			Style style = textPane.addStyle("MyFont", null);
			System.out.println("Font Selected: " + font);
			StyleConstants.setFontFamily(style, font);
	        style = textPane.getStyle("MyFont");
	        doc.setCharacterAttributes(startIndex, endIndex - startIndex, style, false);
		}
	}

	public String[] getFontOption() {
		return fontOption;
	}

	public void setFontOption(String[] fontOption) {
		this.fontOption = fontOption;
	}

}
