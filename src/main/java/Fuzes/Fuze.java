package Fuzes;

import Tnt.Detonatable;

public interface Fuze {    
    public abstract void addDetonator(Detonatable detonator);
    public abstract void removeDetonator(Detonatable detonator);
}
