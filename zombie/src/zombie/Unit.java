package zombie;

import java.util.Random;

abstract public class Unit {
	private String name;
	private int pos;
	private int hp;
	private int max;
	private boolean isEnemy;
	Random r;
	
	public Unit(String name, int pos, int hp, int max, boolean isEnemy) {
		this.name = name;
		this.pos = pos;
		this.hp = hp;
		this.max = max;
		this.isEnemy = isEnemy;	// 몬스터 여부
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
	
	public void move() {
		if(this.pos <= 10) {
			this.pos ++;
			System.out.println("오른쪽으로 이동. 현재 위치는 " + pos);
		}
	}
	
	abstract void attack(Unit unit);
}
