package br.com.goomie.model.account.enums;

public enum Nature {

    DEBTOR ('D',"Devedora"),
    CREDITOR ('C', "Credora"),
    UNDEFINED ('I', "Indefinida")
    ;

    // ATRIBUTOS

    private char type;
    private String label;

    // CONSTRUTOR
    Nature (char type, String label) {
        this.type = type;
        this.label = label;
    }

    // GETTERS
    public char getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }
}
