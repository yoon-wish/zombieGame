package zombie;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private static int SIZE = 20;
	private static int HERO_POS = 1;
	
	private Scanner sc = new Scanner(System.in);
	private Random rand = new Random();
	private int pos;
	private boolean isRun;
	private int[] map;
	
	Hero hero = null;
	Beginner biginner = new Beginner("Beginner", 5, 70, 6, true);
	Intermediate intermediate = new Intermediate("Intermediate", 6, 80, 7, true);
	Advanced advenced = new Advanced("Advanced", 7, 90, 8, true);
	Legendary legendary = new Legendary("Legendary", 9, 200, 15, 100, true);
	
	private Game() {
		setGame();
	}

	private static Game instance = new Game();

	public static Game getInstance() {
		return instance;
	}

	private void setGame() {
		pos = 0;
		isRun = true;
		map = new int[SIZE];
		start();
		setName();
		hero.setPos(pos);
		map[hero.getPos()] = HERO_POS;
	}
	
	private void setName() {
		String name = inputHeroName();
		hero = new Hero(name, 1, 200, 20, 3, false);
	}
	
	private void start() {
		System.out.println("⭒❃.✮:▹　 ZOMBIE GAME　◃:✮.❃⭒");		
	}
	
	private String inputHeroName() {
		System.out.print(">>> Hero Name: ");
		return inputString();
	}
	
	private void printMap() {
		for(int i=0; i<SIZE; i++) {
			System.out.printf("%2d", i+1);
			if(map[i] == HERO_POS) {
				System.out.println("├─🤴─┤");
			} else
				System.out.println("├────┤");
		}
	}

	private void move() {
		System.out.printf("[%s] 위치 = %d\n", hero.getName(), pos);
		System.out.print("앞으로 이동하기(1), 종료하기(2) : ");

		int move = sc.nextInt();

		if (move == 1) {
			map[pos] = 0;
			pos = pos + 1;
			hero.setPos(pos);
			map[pos] = HERO_POS;

			int heroPos = hero.getPos();
			if (heroPos == biginner.getPos()) {
				runGame(biginner);
			} else if (heroPos == intermediate.getPos()) {
				runGame(intermediate);
			} else if (heroPos == advenced.getPos()) {
				runGame(advenced);
			} else if (heroPos == legendary.getPos()) {
				meetMonster(legendary);
				while (checkWin(legendary)) {
					battleMonster(legendary);
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
				if (monster == advenced)
					advenced.hit(hero);
				else if (monster == intermediate)
					intermediate.hit(hero);
			}

		}

	}

	private Unit findUnit(Unit unit) {
		if (unit.getName().equals("Zombie"))
			return intermediate;
		else if (unit.getName().equals("Dracula"))
			return advenced;

		return unit;
	}

	private void meetMonster(Unit unit) {
		System.out.printf("▶ %s를 만났습니다.\n공격모드로 바뀝니다.\n", unit.getName());
	}

	private void battleMonster(Unit unit) {
		System.out.print("\n공격하기(1), 포션마시기(2) : ");
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
			printDead();
			System.out.println("- The End -");
			isRun = false;
			return false;
		}

		if (unit.getHp() <= 0) {
			System.out.printf("\n** %s를 이겼습니다.", unit.getName());
			if (unit.getName().equals("Boss")) {
				System.out.println(" 게임에서 승리했습니다 **\n종료합니다.");
				isRun = false;
				return false;
			} else {
				System.out.println(" 이동할 수 있습니다 **\n");
				return false;
			}
		}
		return true;
	}
	
	private void printDead() {
		try {
			System.out.println("하 아프다 ....");
			Thread.sleep(600);
			System.out.println("......");
			Thread.sleep(600);
			System.out.println("....");
			Thread.sleep(600);
			System.out.println("..");
			Thread.sleep(600);
			System.out.println("점점 고통이 희미해져 간다");
			Thread.sleep(600);
			System.out.println("......");
			Thread.sleep(600);
			System.err.printf("%s는 좀비가 되었다..!\n", hero.getName());
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
	
	private String inputString() {
		return sc.next();
	}

	public void run() {
		while (isRun) {
			printMap();
			move();
		}
	}
}
