package zombie;

public class Zombie extends Unit {
	int power;
	String name = "Zombie";
	
	public Zombie(int pos, int hp, int max, boolean isEnemy) {
		super(pos, hp, max, isEnemy);
	}
	
	@Override
	void attack(Unit hero) {
		 power = r.nextInt(this.getMax()) + 1;
		 
		 hero.setHp(hero.getHp() - power);
		 if(hero.getHp() <= 0)
			 hero.setHp(0);
		 
		 this.setHp(this.getHp() + power/2);	// 자신 회복
		 
		 System.out.printf("%s가 %d의 공격력으로 공격 : 현재 Hero hp: %d, 좀비 체력 회복 %d", name, power, hero.getHp(), this.getHp());
	}
}
