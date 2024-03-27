package zombie;

public class Zombie extends Unit implements Hitable {
	int power;

	public Zombie(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}

	@Override
	public void attack(Unit hero) {
		power = (r.nextInt(this.getMax()) + 1) / 2;

		hero.setHp(hero.getHp() - power);
		if (hero.getHp() <= 0)
			hero.setHp(0);

		if (getHp() + power / 2 > this.MAX_HP)
			setHp(this.MAX_HP);
		else
			setHp(getHp() + power / 2);

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n♥ 현재 [★히어로] hp: %d, [%s] 체력 회복 %d\n", this.getName(), power, hero.getHp(),
				this.getName(), this.getHp());
	}

	@Override
	public void hit(Unit hero) {
		power = r.nextInt(3) + 1;	// 1 ~ 3 추가공격
		
		hero.setHp(hero.getHp() - power);
		if(hero.getHp() <= 0)
			hero.setHp(0);
		
		System.err.printf("\n[%s]가 %d의 공격력으로 추가공격!\n너무 빨라서 피할 수 없었다.\n", this.getName(), power);
		System.out.printf("♥ 현재 [★히어로] hp: %d\n", hero.getHp());
	}
}
