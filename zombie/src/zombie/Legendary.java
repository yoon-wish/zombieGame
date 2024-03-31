package zombie;

import java.util.Random;

public class Legendary extends Intermediate {
	Random rand = new Random();

	private int shield;

	public void setShield(int value) {
		shield = value;
	}

	public int getShield() {
		return shield;
	}

	public Legendary(String name, int pos, int hp, int max, int shield, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
		this.shield = shield;
	}
	
	@Override
	public void attack(Unit hero) {
		int a = rand.nextInt(4) + 1;
		int power = rand.nextInt(getMax())+ 1;
		
		if(a == 1) {
			System.out.println("\n🚫보스의 필살기 발동 2배의 공격력🚫");
			super.attackHero(hero, power * 2);
			System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power * 2);
		} else {
			System.out.println("\n🗡️보스의 일반공격");
			super.attackHero(hero, power);
			System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		}
		
		if (power / 2 > 0) {
			if(this.getHp() + power/2 >= this.getMax()) {
				this.setHp(this.getHp());
				return;
			}
			this.setHp(this.getHp() + (power / 2));
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}
}
