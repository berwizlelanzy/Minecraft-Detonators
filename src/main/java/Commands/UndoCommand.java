package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.UndoState;

public class UndoCommand implements CommandPattern {
    @Override
    public void execute() {
        PlayerRClickListener.getInstance().setState(new UndoState());
    }
}
