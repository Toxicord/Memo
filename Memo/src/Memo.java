
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.*;





public class Memo {

	private JFrame frame;
	private JButton save;
	private JTextArea textArea;
	
	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Memo window = new Memo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		);
	}

	
	//Creates application call to initialize method
	
	public Memo() {
		initialize();
	}

	//initialize method
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(168, 72, 605, 704);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 22, 585, 657);
		panel.add(tabbedPane);
        Font font = new Font("Serif", Font.ITALIC, 20);
        
        JTextArea textArea = new JTextArea(40,5);
        //tabbedPane.addTab("New tab", null, textArea, null);
        

        
        JScrollPane scrollPane = new JScrollPane(textArea);
        tabbedPane.addTab("New tab", null, scrollPane, null);
        
        
        JTextPane textPane_2 = new JTextPane();
        tabbedPane.addTab("New tab", null, textPane_2, null);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        toolBar.setBounds(61, 22, 64, 24);
        frame.getContentPane().add(toolBar);
        
        
        
        /* Font testing below
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
        */
        
        
        
        
        
        JTextPane textPane = new JTextPane();
        TextArea text = new TextArea();
        textPane.setFont(font);
        textPane.setForeground(Color.blue);
        textPane.add(text, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("+");
		tabbedPane.setTabComponentAt(1, btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextArea textArea = new JTextArea(40,5);
				JScrollPane scrollPane1 = new JScrollPane(textArea);
				tabbedPane.insertTab("New tab", null, scrollPane1, null, tabbedPane.getTabCount() - 1);
			}});
		
		//below is daytons save testing (from this line to 
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
        //end daytons save testing
        
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenu mnWindows = new JMenu("Windows");
		menuBar.add(mnWindows);
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
}
