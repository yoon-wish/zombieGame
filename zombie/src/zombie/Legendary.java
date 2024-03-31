package zombie;

import java.util.Random;

public class Legendary extends Intermediate {
	Random r = new Random();

	private int shield;

	public void setShield(int value) {
		shield = value;
	}

	public int getShield() {
		return shield;
	}

	public Legendary(String name, int pos, int hp, int max, int shield, boolean isEnemy) {
		super("Boss", pos, hp, max, isEnemy);
		this.shield = shield;
	}
	
	@Override
	public void attack(Unit hero) {
		int a = r.nextInt(4) + 1;
		int power = r.nextInt(getMax())+ 1;
		
		if(a == 1) {
			System.out.println("🚫보스의 필살기 발동 2배의 공격력🚫");
			hero.setHp(hero.getHp() - (power * 2));
			if(hero.getHp() <= 0)
				hero.setHp(0);
		} else {
			System.out.println("보스의 일반공격");
			hero.setHp(hero.getHp() - power);
			if(hero.getHp() <= 0)
				hero.setHp(0);
		}
		
		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		if (power / 2 > 0) {
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}
}
