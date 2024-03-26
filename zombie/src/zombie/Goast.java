package zombie;

public class Goast extends Unit {
	int power;
	String name;
	
	public Goast(int pos, int hp, int max, boolean isEnemy) {
		super(pos, hp, max, isEnemy);
		this.name  = "Goast";
	}
	
	@Override
	void attack(Unit hero) {
		 power = (r.nextInt(this.getMax()) + 1) / 2;
		 
		 hero.setHp(hero.getHp() - power);
		 if(hero.getHp() <= 0)
			 hero.setHp(0);
		 
		 this.setHp(this.getHp() + power/2);	// 자신 회복
		 
		 System.out.printf("%s가 %d의 공격력으로 공격 : 현재 Hero hp: %d, %s 체력 회복 %d\n", name, power, hero.getHp(), name, this.getHp());
	}
}
