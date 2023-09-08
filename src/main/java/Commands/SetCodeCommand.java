package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.CodeState;

public class SetCodeCommand implements CommandPattern {
    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new CodeState(this.param));
    }
}
