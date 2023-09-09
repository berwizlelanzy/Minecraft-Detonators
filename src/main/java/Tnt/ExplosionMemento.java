package Tnt;

import java.util.Vector;

import ExplosionTypes.ExplosionType;

public class ExplosionMemento {
    private Vector<ExplosionType> history;

    public void add(ExplosionType type) {
        history.add(type);
    }

    public ExplosionType undo() {
        try {
            return history.remove(history.size() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
