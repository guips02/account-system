package br.com.goomie.model.account.enums;

public enum Group {

    ASSET ("Ativo"),
    LIABILITY ("Passivo"),
    SHAREHOLDER_EQUITY("Patrimônio Líquido")
    ;

    private String label;

    Group(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
