package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.TimeState;

public class SetTimeCommand implements CommandPattern {
    private int param;

    public void setParam(int param) {
        this.param = param;
    }

    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new TimeState(this.param));
    }
}
