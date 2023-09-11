package UI;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Commands.DetonateCommand;
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

        CustomButton setCode = new CustomButton();
        setCode.setMediator(mediator);
        mediator.setCode = setCode;

        setCode.setText("Set Code");
        setCode.setVisible(false);
        setCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCode.getMediator().notify(setCode);
            }
        });

        JTextField setCodeField = new JTextField();
        setCodeField.setVisible(false);
        mediator.setCodeField = setCodeField;

        panel.add(masterSetButton);
        panel.add(setTnt);
        panel.add(setCode);
        panel.add(setCodeField);

        // DETONATE --------------------------------------------------

        CustomButton masterDetonate = new CustomButton();
        mediator.detMaster = masterDetonate;
        masterDetonate.setMediator(mediator);

        masterDetonate.setText("Detonate");
        masterDetonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masterDetonate.getMediator().notify(masterDetonate);
            }
        });

        CustomButton detRemote = new CustomButton();
        mediator.detRemote = detRemote;
        detRemote.setMediator(mediator);

        detRemote.setText("DetonateRemote");
        detRemote.setVisible(false);
        
        DetonateCommand detCmd = new DetonateCommand();
        detCmd.setParam("remote");
        detRemote.setCommand(detCmd);

        detRemote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detRemote.getMediator().notify(detRemote);
                detRemote.getCommand().execute();
            }
        });

        CustomButton detCode = new CustomButton();
        mediator.detCode = detCode;
        detCode.setMediator(mediator);

        detCode.setText("Detonate Code");
        detCode.setVisible(false);
        detCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detCode.getMediator().notify(detCode);
            }
        });

        JTextField detCodeField = new JTextField();
        detCodeField.setVisible(false);
        mediator.detCodeField = detCodeField;

        panel.add(masterDetonate);
        panel.add(detRemote);
        panel.add(detCode);
        panel.add(detCodeField);

        frame.add(panel);
        frame.setVisible(true);
    }
}
