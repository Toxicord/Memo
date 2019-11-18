import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.text.*;

public class TextHighlight {
	private JTextArea textarea;
	private JComboBox colorbox;
	private JTextField top;
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

	public TextHighlight() {
		grayPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);
		yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
		orangePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);
		pinkPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
		firstUpdateIndex = -1;
		counter = 0;
	}

	private void createAndDisplayGUI() {
		final JFrame frame = new JFrame("Text HIGHLIGHT");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50),
				"Highlighter JTextArea"));

		textarea = new JTextArea(15, 20);
		JScrollPane scrollPane = new JScrollPane(textarea);
		contentPane.add(scrollPane);

		JButton button = new JButton("ADD HIGHLIGHT TO THE TEXT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String text = null;
				text = textarea.getSelectedText();
				if (text != null && text.length() > 0) {
					int startIndex = textarea.getText().indexOf(text);
					int endIndex = startIndex + text.length();
					Highlighter highlighter = textarea.getHighlighter();

					int selection = JOptionPane.showConfirmDialog(frame, getOptionPanel(), "Highlight Color : ",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (selection == JOptionPane.OK_OPTION) {
						String color = (String) colorbox.getSelectedItem();
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
					} else if (selection == JOptionPane.CANCEL_OPTION) {
						System.out.println("CANCEL BUTTON PRESSED.");
					} else if (selection == JOptionPane.CLOSED_OPTION) {
						System.out.println("Textchart Closed");
					}
				}
			}
		});

		frame.add(contentPane, BorderLayout.CENTER);
		frame.add(button, BorderLayout.PAGE_END);

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	private JPanel getOptionPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
				" COLOR OPTIONS "));
		panel.setLayout(new GridLayout(5, 5, 20, 20));

		JLabel colourLabel = new JLabel("Select COLOR : ");
		colorbox = new JComboBox(colorOptions);

		panel.add(colourLabel);
		panel.add(colorbox);

		return panel;
	}

	public static void main(String... args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TextHighlight().createAndDisplayGUI();
			}
		});
	}
}