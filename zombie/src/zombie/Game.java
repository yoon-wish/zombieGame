package zombie;

public class Game {
	private Game() {
		
	}
	
	private static Game instance = new Game();
	public static Game getInstance() {
		return instance;
	}
	
	private void setGame() {
		Hero hero = new Hero(1, 200, 20, 3, false);
		Goast Goast = new Goast(5, 70, 6, true);
		Zombie zombie = new Zombie(6, 80, 7, true);
		Dracula Dracula = new Dracula(7, 90, 8, true);
		Boss bos = new Boss(9, 200, 15, 100, true);
		
	}
	public void run() {
		setGame();
	}
}
