package zombie;

public class Dracula extends Unit {
	int power;
	
	public Dracula(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}
	
	@Override
	void attack(Unit hero) {
		 power = (r.nextInt(this.getMax()) + 1) / 2;
		 
		 hero.setHp(hero.getHp() - power);
		 if(hero.getHp() <= 0)
			 hero.setHp(0);
		 
		 this.setHp(this.getHp() + power/2);	// 자신 회복
		 
		 System.out.printf("[%s]가 %d의 공격력으로 공격\n현재 [Hero] hp: %d, [%s] 체력 회복 %d\n", this.getName(), power, hero.getHp(), this.getName(), this.getHp());	}
}
