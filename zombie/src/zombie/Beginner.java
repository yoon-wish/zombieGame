package zombie;

public class Beginner extends Unit{
	int power;

	public Beginner(String name, int pos, int hp, int max, boolean isEnemy) {
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

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n♥ 현재 [★히어로] hp: %d, [%s] 체력 회복 %d\n", this.getName(), power, hero.getHp(),
				this.getName(), this.getHp());
	}
	
}
