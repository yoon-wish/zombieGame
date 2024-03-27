package zombie;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private Scanner sc = new Scanner(System.in);
	private Random rand = new Random();
	private int pos;
	private boolean isRun;

	Hero hero = new Hero("Hero", 1, 200, 20, 3, false);
	Goast goast = new Goast("Goast", 5, 70, 6, true);
	Zombie zombie = new Zombie("Zombie", 6, 80, 7, true);
	Dracula dracula = new Dracula("Dracula", 7, 90, 8, true);
	Boss boss = new Boss("Boss", 9, 200, 15, 100, true);

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
		System.out.print("앞으로 이동하기(1), 종료하기(2) : ");

		int move = sc.nextInt();

		if (move == 1) {
			pos = pos + 1;
			hero.setPos(pos);

			int heroPos = hero.getPos();
			if (heroPos == goast.getPos()) {
				runGame(goast);
			} else if (heroPos == zombie.getPos()) {
				runGame(zombie);
			} else if (heroPos == dracula.getPos()) {
				runGame(dracula);
			} else if (heroPos == boss.getPos()) {
				while (checkWin(boss)) {
					meetMonster(boss);
				}
			}
		} else if (move == 2) {
			isRun = false;
		}
	}

	private void runGame(Unit unit) {
		meetMonster(unit);
		while (checkWin(unit)) {
			battleMonster(unit);
			if (randomNum() == 0) {
				Unit monster = findUnit(unit);
				if (monster == dracula)
					dracula.hit(hero);
				else if (monster == zombie)
					zombie.hit(hero);
			}

		}

	}

	private Unit findUnit(Unit unit) {
		if (unit.getName().equals("Zombie"))
			return zombie;
		else if (unit.getName().equals("Dracula"))
			return dracula;

		return unit;
	}

	private void meetMonster(Unit unit) {
		System.out.printf("%s를 만났습니다. 공격모드로 바뀝니다.\n", unit.getName());
	}

	private void battleMonster(Unit unit) {
		System.out.print("공격하기(1), 포션마시기(2)");
		int sel = sc.nextInt();

		if (sel == 1) {
			unit.attack(hero);
			hero.attack(unit);
		} else if (sel == 2) {
			hero.recover();
		}

	}

	private int randomNum() {
		return rand.nextInt(5);
	}

	private boolean checkWin(Unit unit) {
		if (hero.getHp() <= 0) {
			System.err.println("Hero가 죽었습니다. 배틀에서 패배하였습니다.");
			isRun = false;
			return false;
		}

		if (unit.getHp() <= 0) {
			System.out.printf("%s를 이겼습니다.", unit.getName());
			if (unit.getName().equals("Boss")) {
				System.out.println(" 게임에서 승리했습니다. 종료합니다.");
				isRun = false;
				return false;
			} else {
				System.out.println(" 이동할 수 있습니다.");
				return false;
			}
		}
		return true;
	}

	public void run() {
		while (isRun) {
			move();
		}
	}
}
