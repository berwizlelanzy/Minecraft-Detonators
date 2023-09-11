package UI;

import javax.swing.JButton;

import Commands.CommandPattern;

public class CustomButton extends JButton {
    private UIMediator mediator;
    private CommandPattern command;

    public void setMediator(UIMediator mediator) {
        this.mediator = mediator;
    }

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

    public CommandPattern getCommand() {
        return this.command;
    }

    public UIMediator getMediator() {
        return this.mediator;
    }
}
