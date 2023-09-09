package Commands;

import EventListeners.TntDestroyListener;
import Fuzes.Visitor;
import Fuzes.Enums.FuzeType;
import Tnt.Tnt;
import TntCollection.TntIterator;

public class DetonateAllCommand implements CommandPattern {
    @Override
    public void execute() {
        Visitor v = new Visitor();

        TntIterator iter = TntDestroyListener.getInstance().getTntsIterator();

        while (iter.hasNext()) {
            Tnt tnt = iter.getNext().getTnt();

            tnt.getFuze().accept(v);
        }

        TntDestroyListener.getInstance().clearTnt(FuzeType.FUZE_REMOTE);
        TntDestroyListener.getInstance().clearTnt(FuzeType.FUZE_CODE);
    }
}
