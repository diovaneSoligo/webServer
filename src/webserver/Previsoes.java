/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

/**
 *
 * @author diovaneS
 */
public class Previsoes {
    
    private String data;
    private String descricao;
    private String temperatura_max;
    private String temperatura_min;
    private String imagem;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTemperatura_max() {
        return temperatura_max;
    }

    public void setTemperatura_max(String temperatura_max) {
        this.temperatura_max = temperatura_max;
    }

    public String getTemperatura_min() {
        return temperatura_min;
    }

    public void setTemperatura_min(String temperatura_min) {
        this.temperatura_min = temperatura_min;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
}
