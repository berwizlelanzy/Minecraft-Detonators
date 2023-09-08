package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.RemoteState;

public class SetTntCommand implements CommandPattern {
    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new RemoteState());
    }
}
