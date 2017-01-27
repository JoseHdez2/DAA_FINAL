package view.cli;

public class CommandLineInterface {
	enum State {
		MAIN_MENU,
		PROB_DIVERSITY,
		PROB_DISPERSION,
		PROB_TSP,
	}
	
	CommandLineInterface(State cState){
		switch(cState){
		case MAIN_MENU:
			mainMenu();
			break;
		case PROB_DISPERSION:
			break;
		case PROB_DIVERSITY:
			break;
		case PROB_TSP:
			break;
		}
		System.exit(0);
	}
	
	public void mainMenu(){
		System.out.println("Main Menu:");
		System.out.println("");
	}
}
