package UI;

import javax.swing.JTextField;

public class UIMediator {
    private boolean set;
    
    public CustomButton setMaster;
    public CustomButton setTnt;
    public CustomButton setCode;
    public JTextField setCodeField;

    public void notify(CustomButton btn) {
        if (btn.equals(setMaster)) {
            toogleSet();
        } else if (btn.equals(setTnt)) {
            this.set = false;
        } else if (btn.equals(setCode)) {
            this.set = false;
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
    }

    private void toogleSet() {
        set = !set;
    }
}
