package TntCollection;

import Tnt.Tnt;

public class TntElement {
    private Tnt tnt;
    private TntElement next;
    private TntElement prev;

    public TntElement(Tnt tnt){
        this.tnt = tnt;
    }

    public void setNext(TntElement next){
        this.next = next;
    }

    public TntElement getNext() {
        return next;
    }

    public TntElement getPrev() {
        return this.prev;
    }

    public void setPrev(TntElement prev){
        this.prev = prev;
    }
    
    public Tnt getTnt() {
        return this.tnt;
    }
}
