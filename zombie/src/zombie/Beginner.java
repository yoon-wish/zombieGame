package zombie;

public class Beginner extends Unit {
	int power;

	public Beginner(String name, int pos, int hp, int max, boolean isEnemy) {
		super(name, pos, hp, max, isEnemy);
	}

	@Override
	public void attack(Unit hero) {
		power = (rand.nextInt(this.getMax()) + 2) / 2;

		Hero h = (Hero) hero;
		if(h.getShield() > 0) { // 실드가 존재하면
			int temp = h.getShield() - power;
			if(temp >=0) {	// 실드를 부시지 못했을 때
				h.setShield(h.getShield() - power);
			} else {	// 실드를 부셨을 때
				h.setShield(0);
				h.setHp(h.getHp() - temp);
			}
		} else 						// 실드가 존재하지 않으면
			h.setHp(h.getHp() - power);
		
		if(h.getHp() <= 0 )
			h.setHp(0);

		// 흡혈
		if (getHp() + (power / 2) > this.MAX_HP)
			setHp(this.MAX_HP);
		else
			setHp(getHp() + (power / 2));

		System.out.printf("\n[%s]가 %d의 공격력으로 공격\n", this.getName(), power);
		if (power / 2 > 0) {
			System.out.printf("✧ :-흡혈-: ✧\n[%s] %d만큼 체력 회복\n", this.getName(), power / 2);
		}
	}

}
