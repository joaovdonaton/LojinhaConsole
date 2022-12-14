package Objetos;

import Interfaces.Armazenavel;
import Interfaces.FormatoDB;

import java.util.Objects;

public class Produto implements Armazenavel {
    private String nome;
    private String descricao;
    private float preco;
    private int qnt_estoque;

    public static final FormatoDB<Produto> FORMATO_DB = (linha -> {
        String[] dados = linha.split("\\|");
        return new Produto(dados[0], dados[1], Float.parseFloat(dados[2]), Integer.parseInt(dados[3]));
    });

    public Produto(String nome, String descricao, float preco, int qnt_estoque){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.qnt_estoque = qnt_estoque;
    }

    /**
     * @return retorna o produto como String no formato NOME|DESCRIÇÃO|PREÇO|ESTOQUE
     */
    public String toString(){
        return this.getNome() + "|" + this.getDescricao() + "|" + getPrecoFormatado().replace(',', '.')
                + "|" + this.getQnt_estoque();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Produto produto) {
            return (this == obj) || this.getNome().equalsIgnoreCase(produto.getNome());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getNome() {
        return nome;
    }


    public String getDescricao() {
        return descricao;
    }


    public double getPreco() {
        return preco;
    }

    public String getPrecoFormatado(){
        return String.format("%.2f", this.preco);
    }

    public int getQnt_estoque() {
        return qnt_estoque;
    }

    public void removerEstoque(int quantidade){
        if(qnt_estoque - quantidade >= 0) this.qnt_estoque -= quantidade;
        else{
            throw new IllegalArgumentException("Não é possível remover uma quantidade maior que o estoque");
        }
    }

    public void adicionarEstoque(int quantidade){
        this.qnt_estoque += quantidade;
    }

    @Override
    public String criarLinha() {
        return this.toString();
    }
}
