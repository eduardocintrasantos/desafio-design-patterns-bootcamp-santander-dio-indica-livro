package org.example.desafiodesignpatternsbootcampsantanderdio.model;

public enum Nota {
    RUIM(1, "Ruim"),
    REGULAR(2, "Regular"),
    BOM(3, "Bom"),
    MUITO_BOM(4, "Muito bom"),
    EXCELENTE(5, "Excelente");

    private final int valor;
    private final String descricao;

    Nota(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Nota fromValor(int valor) {
        for (Nota nota : values()) {
            if (nota.valor == valor) {
                return nota;
            }
        }
        throw new IllegalArgumentException("Nota inválida: " + valor + ". Use um valor entre 1 e 5.");
    }
}
