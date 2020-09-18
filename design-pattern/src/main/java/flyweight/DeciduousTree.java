package flyweight;

import java.time.LocalDate;

public class DeciduousTree implements Tree {
	// complex trunk, branch, leaf graphic data
	public void display(int x, int y) {
		System.out.println("Deciduous tree is located at " + x + ", " + y);
		if (!this.isWithinRange(LocalDate.now())) {
			System.out.println("The tree currently has no leaves");
		}
	}
}