package zombie;

public class Intermediate extends Unit implements Hitable {
	int power;

	public Intermediate(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}

	@Override
	public void attack(Unit hero) {
		power = (rand.nextInt(this.getMax()) + 2) / 2;

		super.attackHero(hero, this.power);

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		if (power / 2 > 0) {
			this.setHp(this.getHp() + (power / 2));
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}

	@Override
	public void hit(Unit hero) {
		power = rand.nextInt(5) + 1;	// 1 ~ 3 추가공격
		
		hero.setHp(hero.getHp() - power);
		if(hero.getHp() <= 0)
			hero.setHp(0);
		
		System.out.println("┌─────────────────────────────────────────────────────┐");
		System.out.printf("  [%s]가 %d의 공격력으로 뒷통수 가격!\n", this.getName(), power);
		System.out.println("              너무 빨라서 피할 수 없었다.");
		System.out.println("└─────────────────────────────────────────────────────┘\n");
		super.printHp(hero);
	}
}
