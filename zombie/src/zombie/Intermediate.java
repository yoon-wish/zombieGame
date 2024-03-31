package zombie;

public class Intermediate extends Unit implements Hitable {
	int power;

	public Intermediate(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}

	@Override
	public void attack(Unit hero) {
		power = (r.nextInt(this.getMax()) + 2) / 2;

		hero.setHp(hero.getHp() - power);
		if (hero.getHp() <= 0)
			hero.setHp(0);

		if (getHp() + power / 2 > this.MAX_HP)
			setHp(this.MAX_HP);
		else
			setHp(getHp() + power / 2);

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		if (power / 2 > 0) {
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}

	@Override
	public void hit(Unit hero) {
		power = r.nextInt(5) + 1;	// 1 ~ 3 추가공격
		
		hero.setHp(hero.getHp() - power);
		if(hero.getHp() <= 0)
			hero.setHp(0);
		
		System.err.printf("\n[%s]가 %d의 공격력으로 추가공격!\n너무 빨라서 피할 수 없었다.\n", this.getName(), power);
		super.printHp(hero);
	}
}
