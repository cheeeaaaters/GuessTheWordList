package to.us.tommyser.superlist;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 538530910647196708L;
	
	private WordList wl;
	private JTextField inputField;
	private JList<String> dispList;
	private JCheckBox exactField;
	
	public MainPanel() {
		
		wl = new WordList();
		wl.load("wordlist.txt");
		
		setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Add");
		addButton.addActionListener((event) -> {
			wl.add(inputField.getText());
			updateDisplay();
		});
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener((event) -> wl.save("wordlist.txt"));
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener((event) -> {
			wl.load("wordlist.txt");
			updateDisplay();
		});
		buttonPanel.add(addButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		exactField = new JCheckBox("Exact Match");
		exactField.addItemListener((event) -> updateDisplay());
		topPanel.add(exactField, BorderLayout.EAST);
		
		inputField = new JTextField();
		inputField.addCaretListener((event) -> updateDisplay());
		topPanel.add(inputField, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		
		dispList = new JList<String>();
		updateDisplay();
		add(new JScrollPane(dispList), BorderLayout.CENTER);
		
	}
	
	private void updateDisplay() {
		dispList.setListData(wl.getFilteredList(inputField.getText(), exactField.isSelected()));
	}

}
