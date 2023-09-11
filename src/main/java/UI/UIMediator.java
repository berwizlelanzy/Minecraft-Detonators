package UI;

import javax.swing.JTextField;

public class UIMediator {
    private boolean set;
    private boolean detonate;
    
    public CustomButton setMaster;
    public CustomButton setTnt;
    public CustomButton setCode;
    public JTextField setCodeField;

    public CustomButton detMaster;
    public CustomButton detRemote;
    public CustomButton detCode;
    public JTextField detCodeField;

    public void notify(CustomButton btn) {
        if (btn.equals(setMaster)) {
            toogleSet();
        } else if (btn.equals(detMaster)) {
            toogleDetonate();
        } else if (btn.equals(setTnt)) {
            this.set = false;
        } else if (btn.equals(detRemote)) {
            this.detonate = false;
        } else if (btn.equals(setCode)) {
            this.set = false;
        } else if (btn.equals(detCode)) {
            this.detonate = false;
        }

        this.updateUI();
    }

    private void updateUI() {
        if (set) {
            this.setTnt.setVisible(true);
            this.setCode.setVisible(true);
            this.setCodeField.setVisible(true);
        } else {
            this.setTnt.setVisible(false);
            this.setCode.setVisible(false);
            this.setCodeField.setVisible(false);
        }

        if (detonate) {
            this.detRemote.setVisible(true);
            this.detCode.setVisible(true);
            this.detCodeField.setVisible(true);
        } else {
            this.detRemote.setVisible(false);
            this.detCode.setVisible(false);
            this.detCodeField.setVisible(false);
        }
    }

    private void toogleSet() {
        set = !set;
    }

    private void toogleDetonate() {
        detonate = !detonate;
    }
}
