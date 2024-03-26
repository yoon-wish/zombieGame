package zombie;

import java.util.Random;

public class Boss extends Zombie {
	Random r = new Random();

	private int shield;

	public void setShield(int value) {
		shield = value;
	}

	public int getShield() {
		return shield;
	}

	public Boss(int pos, int hp, int max, int shield, boolean isEnemy) {
		super(pos, hp, max, isEnemy);
		this.shield = shield;
	}
	
	public void attack(Unit hero) {
		int a = r.nextInt(4) + 1;
		int power = r.nextInt(getMax())+ 1;
		
		if(a == 1) {
			System.out.println("보스의 필살기 발동 2배의 공격력");
			hero.setHp(hero.getHp() - (power * 2));
			if(hero.getHp() <= 0)
				hero.setHp(0);
		} else {
			System.out.println("보스의 일반공격");
			hero.setHp(hero.getHp() - power);
			if(hero.getHp() <= 0)
				hero.setHp(0);
		}
		System.out.printf("보스가 %d의 공격력으로 공격: 현재 Hero hp: %d", power, hero.getHp());
	}
}
