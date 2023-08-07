package Fuzes;

import java.util.Vector;

import EventListeners.TntDestroyListener;
import Fuzes.Enums.FuzeType;
import Tnt.Detonatable;

public class CodeFuze implements Fuze {
    private Vector<Detonatable> detonators;
    private String code;

    public CodeFuze(String code) {
        this.detonators = new Vector<Detonatable>();
        this.code = code;
    }

    public Vector<Detonatable> getDetonators() {
        return this.detonators;
    }

    @Override
    public void addDetonator(Detonatable detonator) {
        this.detonators.add(detonator);
    }

    @Override
    public void removeDetonator() {
        this.detonators.clear();
    }

    public String getCode() {
        return this.code;
    }

    public void trigger() {
        for (Detonatable detonator : this.detonators) {
            detonator.detonate();
        }

        this.detonators.clear();
        TntDestroyListener.getInstance().clearTnt(FuzeType.FUZE_CODE);
    }
}
