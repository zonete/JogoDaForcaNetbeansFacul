/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author jjunior
 */
public class ServidorHandlerTCP extends Thread {

    private Socket socket;
    private PrintWriter output;
    private ServidorMultithreadTCP caller;
    private BufferedReader input;
    public static Integer id;
    public Hashtable<Integer, Integer> hashTipo;

    public ServidorHandlerTCP(Socket socket, PrintWriter output, ServidorMultithreadTCP caller) throws IOException {
        this.socket = socket;
        this.caller = caller;
        this.output = output;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.input.close();
        this.caller.removerCliente(this.output);
        this.socket.close();
    }

    public synchronized void messageDispatcher(String message) throws IOException {
        Hashtable<Integer, Sessao> clientes = this.caller.getClientes();
        String msg[] = message.split("\\|");
        Sessao sessao = null;
        sessao = clientes.get(new TratamentoHash().RetornaIDSessaoUsuario(clientes, Integer.valueOf(msg[0])));
        PrintWriter out = null;
        if (sessao.getId_cliente1().equals(id)) {
            out = sessao.getConexao_cliente1();
        } else {
            out = sessao.getConexao_cliente2();
        }
        out.println(msg[0] + "|" + msg[1] + "|" + msg[2]);
        out.flush();
    }

    public synchronized void escreveparausuario(String msg1, String msg2, Integer id) throws IOException {
        Hashtable<Integer, Sessao> clientes = this.caller.getClientes();
        Sessao sessao = null;
        sessao = clientes.get(new TratamentoHash().RetornaIDSessaoUsuario(clientes, id));
        PrintWriter out = null;
        if (sessao.getId_cliente1().equals(id)) {
            out = sessao.getConexao_cliente1();
        } else {
            out = sessao.getConexao_cliente2();
        }

        out.println(id.toString() + "|" + msg1 + "|" + msg2);
        out.flush();
    }

    public synchronized void escreveparausuario(String msg1, Integer id) throws IOException {
        Hashtable<Integer, Sessao> clientes = this.caller.getClientes();
        Sessao sessao = null;
        sessao = clientes.get(new TratamentoHash().RetornaIDSessaoUsuario(clientes, id));
        PrintWriter out = null;
        if (sessao.getId_cliente1() == id) {
            out = sessao.getConexao_cliente1();
        } else {
            out = sessao.getConexao_cliente2();
        }
        System.out.print(id.toString() + "|" + msg1);
        out.println(id.toString() + "|" + msg1);
        out.flush();
    }

    @Override
    public void run() {
        String message = " ";
        while (message != null && !message.equals("")) {
            try {
                message = this.input.readLine();
                System.out.println(message);
                if (message != null) {
                    String msg[] = message.split("\\|");
                    if ((msg[1].equals("SAIU"))) {
                        Sessao sessao = null;
                        Integer id_a = null;
                        id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                        sessao = this.caller.hashSessao.get(id_a);
                        if (sessao.getMultiplayer()){
                            if (sessao.getId_cliente1() == Integer.parseInt(msg[0])){
                                if (sessao.getId_cliente2() > 0){
                                escreveparausuario("MODOMULTI|SAIUUSER", sessao.getId_cliente2());
                                }
                            }else {
                              escreveparausuario("MODOMULTI|SAIUUSER", sessao.getId_cliente1());                            
                            }                            
                            
                        }                         
                        this.caller.hashSessao.remove(id_a);
                        
                        
                    }
                    if ((msg[1].equals("TERMINOU"))) {
                        System.out.println(msg[0]);
                        Sessao sessao = null;
                        Integer id_a = null;
                        id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                        System.out.println(id_a.toString());
                        sessao = this.caller.hashSessao.get(id_a);
                        if (msg[2].equals("1")) {
                            sessao.ClienteAcertou(Integer.parseInt(msg[0]));
                        }
                        if (!sessao.getMultiplayer()) {
                            escreveparausuario("MODOSINGLE|ALTERAPLACAR|" + sessao.getPontos_cliente1().toString(), Integer.parseInt(msg[0]));
                        }else{
                            if (msg[2].equals("0")){
                                if (sessao.getTurno() == 1){
                                    sessao.setTurno(2);
                                }else{
                                    sessao.setTurno(1);
                                }
                            }
                            escreveparausuario("MODOMULTI|ALTERAPLACAR|" + sessao.getPontos_cliente1().toString()+"|"+sessao.getPontos_cliente2().toString(), sessao.getId_cliente1());
                            escreveparausuario("MODOMULTI|ALTERAPLACAR|" + sessao.getPontos_cliente1().toString()+"|"+sessao.getPontos_cliente2().toString(), sessao.getId_cliente2());
                            if (sessao.getTurno() == 1){
                               escreveparausuario("MODOMULTI|DIGITEPALAVRA|0", sessao.getId_cliente2());
                            }else{
                               escreveparausuario("MODOMULTI|DIGITEPALAVRA|0", sessao.getId_cliente1());
                            }
                                
                        }

                    }
                    if (msg[1].equals("tipo")) {//tipo
                        //multiplayer 2
                        //single 1                        
                        if (msg[2].equals("2")) {
                            if (msg[3].equals("CRIAR")) {
                                Sessao sessao = null;
                                Integer id_a = null;
                                id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                                System.out.println(id_a.toString());
                                sessao = this.caller.hashSessao.get(id_a);
                                sessao.setMultiplayer(Boolean.TRUE);
                                escreveparausuario("MODOMULTI|AGUARDCONEXAO", Integer.parseInt(msg[0]));


                            }
                            if (msg[3].equals("CARREGAR")) {
                                Hashtable<Integer, Sessao> hs = null;
                                hs = this.caller.getClientes();
                                Integer id_aux;
                                Enumeration<Integer> en = hs.keys();
                                while (en.hasMoreElements()) {
                                    Sessao sessao = null;
                                    id_aux = en.nextElement();
                                    sessao = hs.get(id_aux);
                                    if (sessao.getMultiplayer() && sessao.getId_cliente2().equals(0)) {
                                        escreveparausuario("MODOMULTI|CARREGAJTABLE|" + id_aux.toString() + "|" + sessao.getId_cliente1(), Integer.parseInt(msg[0]));
                                    }
                                }

                            }
                            if (msg[3].equals("ENTRAR")) {
                                Hashtable<Integer, Sessao> hs = null;
                                hs = this.caller.getClientes();
                                Sessao sessao = null;
                                sessao = hs.get(Integer.parseInt(msg[4]));
                                if (sessao.getId_cliente2() != 0) {
                                    escreveparausuario("MODOMULTI|MSG|Sala já foi completada.. Tente outra", Integer.parseInt(msg[0]));
                                } else {
                                    Integer id_a = null;
                                    id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                                    hs.remove(id_a);
                                    sessao.setId_cliente2(Integer.parseInt(msg[0]));
                                    sessao.setConexao_cliente2(this.output);
                                    if (sessao.getTurno() == 1) {
                                        escreveparausuario("MODOMULTI|MSG|Usuario ja se conectou.. esperando ele digitar a dica e a palavra", sessao.getId_cliente1());
                                        escreveparausuario("MODOMULTI|DIGITEPALAVRA|0", sessao.getId_cliente2());
                                    } else {
                                        escreveparausuario("MODOMULTI|MSG|Usuario ja se conectou.. esperando ele digitar a dica e a palavra", sessao.getId_cliente2());
                                        escreveparausuario("MODOMULTI|DIGITEPALAVRA|0", sessao.getId_cliente1());
                                    }
                                }


                            }
                            if (msg[3].equals("PALAVRAENVIADA")) {
                                Sessao sessao = null;
                                Integer id_a = null;
                                id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                                System.out.println(id_a.toString());
                                sessao = this.caller.hashSessao.get(id_a);
                                if (sessao.getId_cliente1() == Integer.parseInt(msg[0])) {
                                    escreveparausuario("MODOMULTI|ENVIANDOPALAVRA|" + msg[4] + "|" + msg[5], sessao.getId_cliente2());
                                } else {
                                    escreveparausuario("MODOMULTI|ENVIANDOPALAVRA|" + msg[4] + "|" + msg[5], sessao.getId_cliente1());
                                }


                            }

                        }
                        if (msg[2].equals("1")) {
                            if ((msg[3] != null) && (msg[3].equals("GERARPALAVRA"))) {
                                try {
                                    PalavrasBanco pala = new PalavrasBanco();
                                    pala.PegarAleatoria();
                                    escreveparausuario("MODOSINGLE|ENVIANDOPALAVRA|" + pala.getPalavra() + "|" + pala.getDica(), Integer.parseInt(msg[0]));
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                System.out.println(msg[0]);
                                Sessao sessao = null;
                                Integer id_a = null;
                                id_a = new TratamentoHash().RetornaIDSessaoUsuario(this.caller.getClientes(), Integer.parseInt(msg[0])).intValue();
                                System.out.println(id_a.toString());
                                sessao = this.caller.hashSessao.get(id_a);
                                sessao.setMultiplayer(Boolean.FALSE);
                                try {
                                    PalavrasBanco pala = new PalavrasBanco();
                                    pala.PegarAleatoria();
                                    escreveparausuario("MODOSINGLE|ENVIANDOPALAVRA|" + pala.getPalavra() + "|" + pala.getDica(), Integer.parseInt(msg[0]));
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }

                        }





                    } else if ((msg[1].equals("msg")) || (msg[1] == "msg")) {//msg
                        messageDispatcher(message);
                    }

                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
