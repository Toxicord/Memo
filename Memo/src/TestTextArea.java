package pa2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
public class TestTextArea {

	private JFrame frame;
	private JTextArea tArea;
    private JTextPane jpane;
    private JButton save;

    protected void initUI() {
        JFrame frame = new JFrame(TestTextArea.class.getSimpleName());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1000, 1000);
        frame.getContentPane().setLayout(null);
        
        jpane = new JTextPane();
        
        JPanel panel = new JPanel();
		panel.setBounds(168, 72, 605, 704);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 22, 585, 657);
		panel.add(tabbedPane);
		
        Font font = new Font("Serif", Font.ITALIC, 20);
        JTextArea tArea = new JTextArea(40,5);
        JScrollPane scrollPane = new JScrollPane(tArea);
        tabbedPane.addTab("New tab", null, scrollPane, null);
        
        JTextPane textPane_2 = new JTextPane();
        tabbedPane.addTab("New tab", null, textPane_2, null);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        toolBar.setBounds(61, 22, 64, 24);
        frame.getContentPane().add(toolBar);
        
        /* Combo box for Font*/
        JComboBox comboBox = new JComboBox();
        comboBox.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		scrollPane.setFont(font);
        	}
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Arial", "Tahoma"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
        toolBar.add(comboBox);
        
        TextArea text = new TextArea();
        jpane.setFont(font);
        jpane.setForeground(Color.blue);
        jpane.add(text, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("+");
		tabbedPane.setTabComponentAt(1, btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextArea textArea = new JTextArea(40,5);
				JScrollPane scrollPane1 = new JScrollPane(textArea);
				tabbedPane.insertTab("New tab", null, scrollPane1, null, tabbedPane.getTabCount() - 1);
			}

		});
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenu mnWindows = new JMenu("Windows");
		menuBar.add(mnWindows);
        frame.getContentPane().add(new JScrollPane(jpane));
        frame.getContentPane().add(toolBar, BorderLayout.SOUTH);
        save = new JButton("Save file");
        save.setBounds(135, 23, 87, 23);
        frame.getContentPane().add(save);
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveYourFile();
            }
        });
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
    
    //PLEASE NOTE THE CODE BELOW IS NOT FUNCTIONING PROPERLY BECAUSE IT IS REFERENCE CODE THAT IS NOT MY OWN (I have all of the resources I've used as placeholders and examples from in a folder for end-of-file accreditation in code comprehension via forum examples)
    //I'm using this code for comprehension purposes, the final saveYourFile function will not nearly resemble this one. Currently trying to catch multiple exceptions on one line & I absolutely must get this to work on an instance(or pane) basis. 
    protected void saveYourFile() {
        JFileChooser fileChooser = new JFileChooser();
        int retval = fileChooser.showSaveDialog(save);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File selFile = fileChooser.getSelectedFile();
            if (selFile != null) {
                if (!selFile.getName().toLowerCase().endsWith(".txt")) {
                	selFile = new File(selFile.getParentFile(), selFile.getName() + ".txt");
                }
                try {
                	jpane.write(new OutputStreamWriter(new FileOutputStream(selFile), "utf-8"));
                    Desktop.getDesktop().open(selFile);
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
                new TestTextArea().initUI();
            }
        });
    }
    
}