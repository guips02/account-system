package br.com.goomie.model.account.enums;

public enum Type {

    SYNTHETIC ('S', "Sintético"),
    ANALYTICAL ('A', "Analítica")
    ;

    private char definition;
    private String label;

    Type(char def, String label) {
        this.definition = def;
        this.label = label;
    }

    public char getDefinition() {
        return definition;
    }

    public String getLabel() {
        return label;
    }
}
