package UI;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Commands.SetCodeCommand;
import Commands.SetTntCommand;

public class UI {
    private UIMediator mediator;

    public UI() {
        this.mediator = new UIMediator();
    }

    public void createUI() {
        JFrame frame = new JFrame("Minecraft Detonators Controls");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300,250);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        CustomButton masterSetButton = new CustomButton();
        mediator.setMaster = masterSetButton;

        masterSetButton.setMediator(mediator);
        masterSetButton.setText("Set");
        masterSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterSetButton.getMediator().notify(masterSetButton);
            }
        });

        CustomButton setTnt = new CustomButton();
        setTnt.setMediator(mediator);
        mediator.setTnt = setTnt;

        setTnt.setText("Set TNT");
        setTnt.setVisible(false);
        setTnt.setCommand(new SetTntCommand());
        setTnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTnt.getMediator().notify(setTnt);
                setTnt.getCommand().execute();
            }
        });

        JTextField setCodeField = new JTextField();
        setCodeField.setVisible(false);
        mediator.setCodeField = setCodeField;

        CustomButton setCode = new CustomButton();
        setCode.setMediator(mediator);
        mediator.setCode = setCode;

        SetCodeCommand codeCommand = new SetCodeCommand();
        setCode.setCommand(codeCommand);

        setCode.setText("Set Code");
        setCode.setVisible(false);
        setCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCode.getMediator().notify(setCode);
                final String code = setCodeField.getText();
                ((SetCodeCommand) setCode.getCommand()).setParam(code);
                setCode.getCommand().execute();
            }
        });

        panel.add(masterSetButton);
        panel.add(setTnt);
        panel.add(setCode);
        panel.add(setCodeField);

        frame.add(panel);
        frame.setVisible(true);
    }
}
