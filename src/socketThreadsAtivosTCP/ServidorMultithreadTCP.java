/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jjunior
 */
public class ServidorMultithreadTCP extends Thread {

    private List clientes;
    private ServerSocket server;
    //public Hashtable<Integer, PrintWriter> hash;
    public Hashtable<Integer, Sessao> hashSessao;
    public Integer Id_sessao;
    public Integer Id_cliente;

    public ServidorMultithreadTCP(int porta) throws IOException {
        this.server = new ServerSocket(porta);
        this.clientes = new ArrayList();
    }

    @Override
    public void run() {
        hashSessao = new Hashtable();
        Id_sessao = 10;
        Id_cliente = 100;
        Socket socket = null;
        while (true) {
            try {
                
                socket = this.server.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream());                
                Sessao sessao = new Sessao(Id_cliente, output, Boolean.FALSE);
                hashSessao.put(Id_sessao,sessao);               
                (new ServidorHandlerTCP(socket, output, this)).start();
                output.println(Id_cliente.toString()+"|IDUSUARIO");
                output.flush();
                Id_sessao = Id_sessao + 1;
                Id_cliente = Id_cliente + 1;

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public synchronized void novoCliente(PrintWriter output) throws IOException {
        clientes.add(output);
        output.println(Integer.toString(Id_sessao)+"|"+"Conectado") ;        
        output.flush();
    }

    public synchronized void removerCliente(PrintWriter output) {
        clientes.remove(output);
        output.close();
    }

    public Hashtable<Integer,Sessao> getClientes() {
        return hashSessao;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.server.close();
    }

    public static void main(String[] args) {
        try {
            (new ServidorMultithreadTCP(6789)).start();
             Ser ser = new Ser();
            ser.setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
