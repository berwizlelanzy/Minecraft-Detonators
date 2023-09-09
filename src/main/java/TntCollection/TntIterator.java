package TntCollection;

public class TntIterator {
    private TntElement state;

    public TntIterator(TntElement collection) {
        this.state = collection;
    }

    public TntElement getNext() {
        TntElement tmp = this.state;
        this.state = this.state.getNext();
        return tmp;
    }

    public boolean hasNext() {
        return this.state != null;
    }
}
