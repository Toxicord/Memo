import java.awt.BorderLayout;//this is not my code, this is reference code
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
public class DaytonSavesTabbedPanes {

    private JTextArea textArea;
    private JButton save;//this saves 

    protected void initUI() {
        JFrame frame = new JFrame(DaytonSavesTabbedPanes.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea(24, 80);
        save = new JButton("Save to file");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        frame.add(textArea);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(save);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    protected void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showSaveDialog(save);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getParentFile(), file.getName() + ".txt");
                }
                try {
                    textArea.write(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                    Desktop.getDesktop().open(file);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new DaytonSavesTabbedPanes().initUI();
	            }
	        });
	    }
	}
