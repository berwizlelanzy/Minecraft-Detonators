package Tnt;

import ExplosionTypes.ExplosionType;
import Fuzes.Fuze;

public class Tnt implements Detonatable {
    private Fuze fuze;
    private ExplosionType explosionType;
    private ExplosionMemento memento;

    public Tnt(Fuze fuze, ExplosionType explosionType) {
        this.fuze = fuze;
        this.fuze.addDetonator(this::detonate);
        this.explosionType = explosionType;
        this.memento = new ExplosionMemento();
    }

    public Fuze getFuze() {
        return this.fuze;
    }

    public void disarmFuze() {
        this.fuze.removeDetonator();
    }

    public void armFuze() {
        this.fuze.addDetonator(this::detonate);
    }

    public void setExpType(ExplosionType type) {
        this.memento.add(this.explosionType.clone());
        this.explosionType = type;
    }

    public void undoExpType() {
        ExplosionType type = this.memento.undo();
        
        if (type != null) {
            this.explosionType = type;
        }
    }

    public ExplosionType getExpType() {
        return this.explosionType;
    }

    @Override
    public void detonate() {
        this.explosionType.explode();
    }
}
