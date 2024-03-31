package zombie;

public class Beginner extends Unit {
	int power;

	public Beginner(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}

	@Override
	public void attack(Unit hero) {
		power = (rand.nextInt(this.getMax()) + 2) / 2;

		super.attackHero(hero, this.power);

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		// 흡혈
		if (power / 2 > 0) {
			this.setHp(this.getHp() + (power / 2));
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}

}
