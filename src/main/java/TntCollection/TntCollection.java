package TntCollection;

import Fuzes.CodeFuze;
import Fuzes.RemoteFuze;
import Remote.RemoteBroadcaster;
import Tnt.Tnt;

public class TntCollection {
    private TntElement first;
    private TntElement last;

    public TntIterator createIterator() {
        return new TntIterator(this.first);
    }

    public void add(Tnt tnt) {
        if (first == null) {
            first = last = new TntElement(tnt);
        } else {
            TntElement newTnt = new TntElement(tnt);
            last.setNext(newTnt);
            newTnt.setPrev(last);
            last = newTnt;
        }
    }

    public boolean remove(Tnt tnt) {
        TntElement tmp = this.first;
        boolean found = false;
        
        while (!found) {
            if (tmp.getTnt().getExpType().getBlock().equals(tnt.getExpType().getBlock())) {
                found = true;

                if (tnt.getFuze() instanceof RemoteFuze) {
                    RemoteBroadcaster broadcaster = RemoteBroadcaster.getInstance();
                    broadcaster.removeRemote((RemoteFuze) tmp.getTnt().getFuze());
                } else if (tnt.getFuze() instanceof CodeFuze) {
                    tnt.disarmFuze();
                }

                if (tmp.getPrev() == null) {
                    if (tmp.getNext() == null) {
                        this.first = null;
                        return found;
                    }

                    this.first = tmp.getNext();
                    this.first.setPrev(null);
                } else {
                    tmp.getPrev().setNext(tmp.getNext());
                }
            }

            tmp = tmp.getNext();
        }

        return found;
    }
}
