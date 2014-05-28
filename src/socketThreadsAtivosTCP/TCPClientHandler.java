/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jjunior
 */
public class TCPClientHandler extends Thread {

    private Socket socket;
    private ClienteTCPTeste caller;
    private BufferedReader input;

    public TCPClientHandler(Socket socket, ClienteTCPTeste caller) throws IOException {
        this.socket = socket;
        this.caller = caller;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        String message = " ";
        while (message != null && !message.equals("")) {
            try {
                message = this.input.readLine();
                String msg[] = message.split("\\|");
                System.out.println("MSG0 : " + msg[0]);
                System.out.println("MSG1 : " + msg[1]);
                if (msg[1].equals("MODOSINGLE")) {
                    System.out.println("entrou no modosingle ");
                    if (msg[2].equals("ENVIANDOPALAVRA")) {
                        System.out.println("entrou no enviandopalavra " + msg[3] + "/" + msg[4]);
                        this.caller.CarregaPalavra(msg[3], msg[4]);
                        this.caller.DesenharnoPainel();

                    }
                    if (msg[2].equals("ALTERAPLACAR")) {
                        System.out.println("entrou no alteraplacar");
                        System.out.println(msg[3] + " placar");
                        this.caller.AlteraPlacarPlayerI(msg[3]);
                    }
                }
                if (msg[1].equals("MODOMULTI")) {
                    System.out.println("entrou no modoMulti ");
                    if (msg[2].equals("AGUARDCONEXAO")) {
                        System.out.println("Aguardando Conexao");
                        this.caller.CarregaCTDICA("Aguardando Conexao de Outro Usuario");
                        this.caller.DesabilitarAlfabeto();
                        this.caller.DesenharnoPainel();
                        this.caller.DesabilitaBTn();

                    }
                    if (msg[2].equals("SAIUUSER")) {
                        System.out.println("Outro usuario saiu da sala");
                        this.caller.OptionPaneShow("Outro Jogador SAIU!!");
                        this.caller.DesabilitarAlfabeto();
                        this.caller.CarregaVazioNoCT();
                        this.caller.desconect();
                        

                    }
                    if (msg[2].equals("ENVIANDOPALAVRA")) {
                        System.out.println("entrou no enviandopalavra " + msg[3] + "/" + msg[4]);
                        this.caller.CarregaPalavra(msg[3], msg[4]);
                        //this.caller.DesenharnoPainel();

                    }
                    if (msg[2].equals("DIGITEPALAVRA")){
                        this.caller.HabilitaPalavra();
                        
                    }
                    if (msg[2].equals("ALTERAPLACAR")) {
                        System.out.println("entrou no alteraplacar");
                        System.out.println(msg[3] + " placar");
                        this.caller.AlteraPlacarPlayerI(msg[3]);
                        this.caller.AlteraPlacarPlayerII(msg[4]);

                    }
                    if (msg[2].equals("CARREGAJTABLE")) {
                        System.out.println("entrou no CARREGAJTABLE");
                        System.out.println(msg[3]);
                        System.out.println(msg[4]);


                        String[] txt = {msg[3], msg[4]};
                        this.caller.AddNoModel(txt);

                    }
                    if (msg[2].equals("MSG")) {
                        
                        this.caller.CarregaCTDICA(msg[3]);

                    }

                }
                if (msg[1].equals("IDUSUARIO")) {
                    this.caller.CarregaUsuar(msg[0]);
                }
                this.caller.escreverMensagem(message);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
