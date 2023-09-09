package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.TimeState;

public class SetTimeCommand implements CommandPattern {
    private int param;
    private String type;

    public void setParam(int param) {
        this.param = param;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new TimeState(this.param, this.type));
    }
}
