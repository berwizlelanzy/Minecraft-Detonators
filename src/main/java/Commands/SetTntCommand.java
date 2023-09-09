package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.RemoteState;

public class SetTntCommand implements CommandPattern {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new RemoteState(type));
    }
}
