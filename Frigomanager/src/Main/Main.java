package Main;

import controller.Controller;
import view.Window;

public class Main {
	private static boolean test = true;
	public static void main(String[] args) {
		Controller c = new Controller();
	   
		while (test == true){
			c.run();
		};
	}

}
