/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.io.PrintWriter;

/**
 *
 * @author DaviZonete
 */
public class Sessao {

    public Integer id_cliente1;
    public PrintWriter conexao_cliente1;
    public Integer id_cliente2;
    public PrintWriter conexao_cliente2;
    public Boolean multiplayer;
    public Integer pontos_cliente1;
    public Integer pontos_cliente2;
    public Integer turno = 1;

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }
    
    

    public PrintWriter getConexao_cliente1() {
        return conexao_cliente1;
    }

    public void setConexao_cliente1(PrintWriter conexao_cliente1) {
        this.conexao_cliente1 = conexao_cliente1;
    }

    public PrintWriter getConexao_cliente2() {
        return conexao_cliente2;
    }

    public void setConexao_cliente2(PrintWriter conexao_cliente2) {
        this.conexao_cliente2 = conexao_cliente2;
    }

    public Integer getId_cliente1() {
        return id_cliente1;
    }

    public void setId_cliente1(Integer id_cliente1) {
        this.id_cliente1 = id_cliente1;
    }

    public Integer getId_cliente2() {
        return id_cliente2;
    }

    public void setId_cliente2(Integer id_cliente2) {
        this.id_cliente2 = id_cliente2;
    }

    public Boolean getMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public Sessao(Integer id_cliente1, PrintWriter conexao_cliente1, Boolean multiplayer) {
        this.id_cliente1 = id_cliente1;
        this.conexao_cliente1 = conexao_cliente1;
        this.multiplayer = multiplayer;
        this.id_cliente2 = 0;
        this.pontos_cliente1 = 0;
        this.pontos_cliente2 = 0;

    }

    public Integer getPontos_cliente1() {
        return pontos_cliente1;
    }

    public Integer getPontos_cliente2() {
        return pontos_cliente2;
    }

    public void ClienteAcertou(Integer id) {

        if (!this.getMultiplayer()) {
            pontos_cliente1 = pontos_cliente1 + 1;
        } else {
            if (this.id_cliente1 == id) {
                this.turno = 2;
                pontos_cliente1 = pontos_cliente1 + 2;
                pontos_cliente2 = pontos_cliente2 + 1;
            } else {
                this.turno = 1;
                pontos_cliente1 = pontos_cliente1 + 1;
                pontos_cliente2 = pontos_cliente2 + 2;

            }
        }
    }

}
