package zombie;

class Hero extends Unit implements recoverable{
	int power;
	int count;
	
	public Hero(String name, int pos, int hp, int max, int count, boolean isEnemy) {
		super("Hero", pos, hp, max, isEnemy);
		this.count = count;
	}
	
	@Override
	public void attack(Unit enemy) {
		if(enemy instanceof Boss) {	// 적이 보스일 때
			Boss boss = (Boss) enemy;
			power = r.nextInt(getMax()) + 1;
			
			if(boss.getShield() > 0) { // 실드가 존재하면
				int r = boss.getShield() - power;
				if(r >=0) {	// 실드를 부시지 못했을 때
					boss.setShield(boss.getShield() - power);
				} else {	// 실드를 부셨을 때
					boss.setShield(0);
					boss.setHp(boss.getHp() - r);
				}
			} else 						// 실드가 존재하지 않으면
				boss.setHp(boss.getHp() - power);
			
			if(boss.getHp() <= 0 )
				boss.setHp(0);
			
			System.out.printf("히어로가 %d의 공격력으로 공격\n현재 Boss의 hp: %d 현재 Boss의 Shield: %d\n", power, boss.getHp(), boss.getShield());
		} else {
			power = r.nextInt(getMax()) + 1;
			enemy.setHp(enemy.getHp() - power);
			if(enemy.getHp()<=0)
				enemy.setHp(0);
		}
	}

	@Override
	public void recover() {
		if(count > 0) {
			if(getHp() + 100 > this.MAX_HP)
				setHp(this.MAX_HP);
			else
				setHp(getHp() + 100);
			System.out.println("체력 회복해서" + getHp() +"이 되었습니다.");
			count -= 1;
		} else if(count == 0) {
			System.out.println("모두 사용했습니다.");
		}
		
	}
	
	
}
