package Fuzes;

import java.util.Vector;

public class CodeFuzeFactory {
    private static CodeFuzeFactory instance;
    private Vector<CodeFuze> codeFuzes;

    private CodeFuzeFactory() {
        this.codeFuzes = new Vector<CodeFuze>();
    }

    public static CodeFuzeFactory getInstance() {
        if (instance == null) {
            instance = new CodeFuzeFactory();
        }

        return instance;
    }

    public CodeFuze getFuze(String code) {
        for (CodeFuze codeFuze : this.codeFuzes) {
            if (codeFuze.getCode().equals(code)) {
                return codeFuze;
            }
        }

        CodeFuze newFuze = new CodeFuze(code);
        this.codeFuzes.add(newFuze);
        return newFuze;
    }

    public Vector<CodeFuze> getFuzeList() {
        return this.codeFuzes;
    }
}
