package Fuzes;

public class Visitor {
    public void visit(CodeFuze f) {
        f.trigger();
    }

    public void visit(RemoteFuze f) {
        f.fire();
    }

    public void visit(TimeFuze f) {
        // Formalitas doang 
        // Timefuze pas di set langsung countdown, pas countdown gabisa di apa-in karena dia pake thread.sleep :)
        f.beginCountdown(0);
    }
}
