package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.CodeState;

public class SetCodeCommand implements CommandPattern {
    private String param;
    private String type;

    public void setParam(String param) {
        this.param = param;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute() {
        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        listener.setState(new CodeState(this.param, this.type));
    }
}
