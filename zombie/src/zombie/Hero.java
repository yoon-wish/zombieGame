package zombie;

class Hero extends Unit implements recoverable{
	private int power;
	private int potion;
	private int shield;
	
	
	public Hero(String name, int pos, int hp, int max, int potion, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
		this.potion = potion;
		this.shield = 0;
	}
	
	public String getName() {
		return super.getName();
	}
	
	public int getPos() {
		return super.getPos();
	}
	
	public void getPrintHp() {
	}
	
	public int getShield() {
		return this.shield;
	}
	
	public void setShield(int shield) {
		this.shield += shield;
	}
	
	@Override
	public void attack(Unit enemy) {
		if(enemy instanceof Legendary) {	// 적이 보스일 때
			Legendary boss = (Legendary) enemy;
			power = rand.nextInt(getMax()) + 1;
			
			if(boss.getShield() > 0) { // 실드가 존재하면
				int temp = boss.getShield() - power;
				if(temp >=0) {	// 실드를 부시지 못했을 때
					boss.setShield(boss.getShield() - power);
				} else {	// 실드를 부셨을 때
					boss.setShield(0);
					boss.setHp(boss.getHp() - temp);
				}
			} else 						// 실드가 존재하지 않으면
				boss.setHp(boss.getHp() - power);
			
			if(boss.getHp() <= 0 )
				boss.setHp(0);
			
			System.out.printf("[%s]가 %d의 공격력으로 공격\n♥ 현재 Boss의 hp: %d 현재 Boss의 Shield: %d\n", this.getName(), power, boss.getHp(), boss.getShield());
		} else {
			power = rand.nextInt(getMax()) + 1;
			enemy.setHp(enemy.getHp() - power);
			if(enemy.getHp()<=0) {
				enemy.setHp(0);
			}
			
			System.out.printf("[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
			super.printHp(this);
			super.printHp(enemy);
			System.out.println();
		}
	}

	@Override
	public void recover() {
		if(potion > 0) {
			if(getHp() + 100 > this.MAX_HP)
				setHp(this.MAX_HP);
			else
				setHp(getHp() + 100);
			System.out.println("체력 회복");
			super.printHp(this);
			potion -= 1;
		} else if(potion == 0) {
			System.out.println("모두 사용했습니다.");
		}
		
	}
	
	
}
