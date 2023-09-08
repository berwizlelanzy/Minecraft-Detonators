package Commands;

import java.util.Vector;

import Fuzes.CodeFuze;
import Fuzes.CodeFuzeFactory;
import Remote.RemoteBroadcaster;

public class DetonateCommand implements CommandPattern {
    private String param;

    public void setParam(String mode) {
        this.param = mode;
    }

    @Override
    public void execute() {
        if (this.param.equals("remote")) {
            RemoteBroadcaster.getInstance().fire();
        } else if (this.param.length() == 4) {
            Vector<CodeFuze> codeFuzes = CodeFuzeFactory.getInstance().getFuzeList();
            for (CodeFuze codeFuze : codeFuzes) {
                if (codeFuze.getCode().equals(this.param)) {
                    codeFuze.trigger();
                }
            }
        }
    }
}
