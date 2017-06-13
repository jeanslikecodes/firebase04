package jeancarlos.firebase04.Model;

import java.io.Serializable;

/**
 * Created by Jean Carlos on 11/06/2017.
 */

public class Anuncio implements Serializable {

    private String id;
    private String descricao;
    private String tamanho;
    private float preco;

    public Anuncio() {

    }

    /*public Anuncio(String id, String descricao, String tamanho, float preco) {
        this.id = id;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
    }*/

    public Anuncio( String descricao, String tamanho, float preco) {

        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
}
