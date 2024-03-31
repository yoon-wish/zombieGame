package zombie;

import java.util.Random;

abstract public class Unit {
	public final int MAX_HP;
	public final int MY_HP;

	private String name;
	private int pos;
	private int hp;
	private int[] hpBar;
	private int max;
	private boolean isEnemy;
	public Random rand;

	public Unit(String name, int pos, int hp, int max, boolean isEnemy) {
		if (isEnemy) {
			this.name = name + " Zombie";
		} else
			this.name = name;
		this.pos = pos;
		this.hp = hp;
		this.hpBar = new int[hp / 10];
		this.max = max;
		this.isEnemy = isEnemy; // 몬스터 여부
		this.MAX_HP = hp;
		this.MY_HP = 1;
		rand = new Random();
	}

	public String getName() {
		return this.name;
	}

	public boolean IsEnemy() {
		return isEnemy;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMax() {
		return this.max;
	}

	public int[] getHpBar() {
		return this.hpBar;
	}

	public void printHp(Unit unit) {
		double temp = unit.hp;
		temp = Math.round(temp / 10);
		for (int i = 0; i < unit.hpBar.length; i++) {
			unit.hpBar[i] = 0;
		}

		for (int i = 0; i < temp; i++) {
			unit.hpBar[i] = MY_HP;
		}

		System.out.printf("[%s] HP ", unit.getName());

		for (int i = 0; i < unit.hpBar.length; i++) {
			if (unit.hpBar[i] == MY_HP)
				System.out.print("■");
			else
				System.out.print("□");
		}

		System.out.printf(" (%d/%d)\n", unit.hp, unit.MAX_HP);
	}

	public void printBossShield(Unit unit) {
		Legendary boss = (Legendary) unit;
		
		if(boss.getShield() > 0) {
			double temp = boss.getShield();
			temp = Math.round(temp/10);

			System.out.printf("[%s] shield ", boss.getName());

			for(int i=0; i<temp; i++) {
				System.out.print("🛡");
			}
			
			System.out.printf(" (%d)\n\n", boss.getShield());
		}
	}
	
	public void printHeroShield(Unit unit) {
		Hero hero = (Hero) unit;
		
		if(hero.getShield() > 0) {
			double temp = hero.getShield();
			temp = Math.round(temp/10);

			System.out.printf("[%s] shield ", hero.getName());

			for(int i=0; i<temp; i++) {
				System.out.print("🛡");
			}
			
			System.out.printf(" (%d)\n", hero.getShield());
		}
	}
	
	public void attackHero(Unit hero, int power) {
		Hero h = (Hero) hero;
		if(h.getShield() > 0) { // 실드가 존재하면
			int temp = h.getShield() - power;
			if(temp >=0) {	// 실드를 부시지 못했을 때
				h.setShield(h.getShield() - power);
			} else {	// 실드를 부셨을 때
				h.setShield(0);
				h.setHp(h.getHp() + temp);
			}
		} else 						// 실드가 존재하지 않으면
			h.setHp(h.getHp() - power);
		
		if(h.getHp() <= 0 )
			h.setHp(0);
	}

	public abstract void attack(Unit unit);
}
