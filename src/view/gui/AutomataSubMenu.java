package view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import convenience.gui.CoolFrame;

/**
 * A submenu for the program, each a kind of main menu for each
 * main feature of the program (in this case, 
 * fantastic types problems and how to solve them)
 */
public abstract class AutomataSubMenu extends CoolFrame{

	public AutomataSubMenu() {
		super(AutomataMainMenu.menuDimension, CoolFrame.Type.SubCoolFrame);
	}
	
	// Will hold the only unique view across different sub-menus.
	JPanel submenuSpecificPanel = new JPanel();
	
	@Override
	final protected void addStuffIntoMe() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		setTitle("SubMenu");
		
		this.add(new JLabel("Problem"));
		
		JButton btnDiversity = new JButton("Load");
		btnDiversity.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("User chose to load problem from file.");
            }
        });
		this.add(btnDiversity);

		JButton btnDispersion = new JButton("Generate");
		btnDispersion.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
            	System.out.println("User chose to generate problem.");
            	new GenerateProblemPopUp();
            }
        });
		this.add(btnDispersion);
	}
	
	/**
	 * Add stuff in a smaller AutomataSubMenu-specific panel.
	 */
	abstract protected void addMoreStuffIntoMe();
}
