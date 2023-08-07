package Fuzes;

import Tnt.Detonatable;

public class TimeFuze implements Fuze {
    private Detonatable detonator;

	@Override
	public void addDetonator(Detonatable detonator) {
		this.detonator = detonator;
	}

	@Override
	public void removeDetonator() {
		this.detonator = null;
	}

    public void beginCountdown(int secs) {
        try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        this.detonator.detonate();
    }
}
