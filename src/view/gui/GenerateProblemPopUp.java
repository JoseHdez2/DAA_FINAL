package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import convenience.gui.CoolFrame;

public class GenerateProblemPopUp extends CoolFrame{

	public GenerateProblemPopUp() {
		super(AutomataMainMenu.menuDimension, CoolFrame.Type.SubCoolFrame);
	}

	@Override
	protected void addStuffIntoMe() {
		this.setTitle("Generate Problem...");
		
		JButton btnDiversity = new JButton("Load parameters");
		btnDiversity.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("User chose to load problem from file.");
            }
        });
		this.add(btnDiversity);

		Border paramInpBorder = BorderFactory.createTitledBorder("Manual Parameter Input");
		JPanel paramInpPanel = new JPanel();
		paramInpPanel.setBorder(paramInpBorder);
		this.add(paramInpPanel);
		
		JButton btnDispersion = new JButton("Generate");
		btnDispersion.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("User chose to generate problem.");
            }
        });
		paramInpPanel.add(btnDispersion);
		
		Border paramBorder = BorderFactory.createTitledBorder("Manual Parameter Input");
	}
}
