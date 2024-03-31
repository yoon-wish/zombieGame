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
	public Random r;

	public Unit(String name, int pos, int hp, int max, boolean isEnemy) {
		if (isEnemy) {
			this.name = name + " Zombie";
		} else
			this.name = name;
		this.pos = pos;
		this.hp = hp;
		this.hpBar = new int[hp/10];
		this.max = max;
		this.isEnemy = isEnemy; // 몬스터 여부
		this.MAX_HP = hp;
		this.MY_HP = 1;
		r = new Random();
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
		temp = Math.round(temp/10);
		for(int i=0; i<hpBar.length; i++) {
			hpBar[i] = 0;
		}
		
		for(int i=0; i<temp; i++) {
			hpBar[i] = MY_HP;
		}
		
		for(int i=0; i<hpBar.length; i++) {
			if(hpBar[i] == MY_HP)
				System.out.print("■");
			else
				System.out.print("□");
		}
	}

//	public void printZombieHp(Unit ) {
//		
//	}
	
//	public void move() {
//		if(this.pos <= 10) {
//			this.pos ++;
//			System.out.println("오른쪽으로 이동. 현재 위치는 " + pos);
//		}
//	}

	public abstract void attack(Unit unit);
}
