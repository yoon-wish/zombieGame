package zombie;

import java.util.Scanner;

public class Game {
	private Scanner sc = new Scanner(System.in);
	private int pos;
	private boolean isRun;

	Hero hero = new Hero(1, 200, 20, 3, false);
	Goast goast = new Goast("Goast", 5, 70, 6, true);
	Zombie zombie = new Zombie("Zombie", 6, 80, 7, true);
	Dracula dracula = new Dracula("Dracula", 7, 90, 8, true);
	Boss boss = new Boss(9, 200, 15, 100, true);
	
	private Game() {
		setGame();
	}
	
	private static Game instance = new Game();
	public static Game getInstance() {
		return instance;
	}
	
	private void setGame() {
		pos = 1;
		isRun = true;
		hero.setPos(pos);
	}
	
	private void move() {
		System.out.println("현재 위치 = " + pos);
		System.out.print("앞으로 이동하기(1), 종료하기(2)");
		
		int move = sc.nextInt();
		
		if(move == 1) {
			pos = pos + 1;
			hero.setPos(pos);
			
			int heroPos = hero.getPos();
			if(heroPos == goast.getPos())
				meetMonster(goast);
			else if(heroPos == zombie.getPos())
				meetMonster(zombie);
			else if (heroPos == dracula.getPos())
				meetMonster(dracula);
		} else if(move == 2) {
			isRun = false;
		}
	}
	
	private void meetMonster(Unit unit) {
		System.out.printf("%s를 만났습니다. 공격모드로 바뀝니다.", unit.get);
	}
	
	private void move(int pos) {
		this.pos = pos + 1;
		
	}
	public void run() {
		while(true) {
			move();
		}
	}
}
