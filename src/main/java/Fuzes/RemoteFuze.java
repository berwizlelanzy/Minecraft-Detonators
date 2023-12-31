package Fuzes;

import Remote.RemoteObserver;
import Tnt.Detonatable;

public class RemoteFuze implements Fuze, RemoteObserver{
    private Detonatable detonator;

    @Override
    public void fire() {
        if (this.detonator != null) {
            this.detonator.detonate();
        }
    }

    @Override
    public void addDetonator(Detonatable detonator) {
        this.detonator = detonator;
    }

    @Override
    public void removeDetonator() {
        this.detonator = null;
    }

    @Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
