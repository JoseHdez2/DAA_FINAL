package view.gui;

import javax.swing.JButton;

public class SubMenuDiversity extends AutomataSubMenu{

	@Override
	protected void addMoreStuffIntoMe() {
		setTitle("Maximum Diversity");
		this.add(new JButton("Calculate stuff"));
	}

}
