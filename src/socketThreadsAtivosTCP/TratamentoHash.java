/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author DaviZonete
 */
public class TratamentoHash {

    public Integer id;
    public Integer id_aux;

    public Integer RetornaIDSessaoUsuario(Hashtable<Integer, Sessao> hs, Integer Id_cliente) {
        Enumeration<Integer> en = hs.keys();
        id = 0;
        while (en.hasMoreElements()) {
            Sessao sessao = null;
            id_aux = en.nextElement();
            sessao = hs.get(id_aux);           
            if (sessao.getId_cliente1() == Id_cliente) {
                id = id_aux;
            }
            if (sessao.multiplayer && sessao.getId_cliente2() == Id_cliente){
                id = id_aux;
            }
        }

        return id;


    }
}
