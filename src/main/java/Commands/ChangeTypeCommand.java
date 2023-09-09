package Commands;

import EventListeners.PlayerRClickListener;
import RClickStates.TypeState;

public class ChangeTypeCommand implements CommandPattern{
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute() {
       PlayerRClickListener.getInstance().setState(new TypeState(type)); 
    }
}
