/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

/**
 *
 * @author DaviZonete
 */
public class Verificacao {
     public Boolean VerificaDica(String palavra) {
        Boolean valor;
        valor = true;
        String temp;
        int contador = palavra.length();
        if (contador < 1 || contador > 100){
            valor = false;
        }
        for (int i = 0; i < contador; i++) {
            temp = palavra.substring(i, i + 1).toUpperCase();
            if (temp.equals("|")) {
                valor = false;
            }

        }
        return valor;

    }

    public Boolean VerificaPalavra(String palavra) {
        Boolean valor;
        valor = true;
        String temp;
        int contador = palavra.length();
        if (contador < 1 || contador > 10){
            valor = false;
        }
        for (int i = 0; i < contador; i++) {
            temp = palavra.substring(i, i + 1).toUpperCase();
            if ((!temp.equals("A")) && (!temp.equals("B")) && (!temp.equals("C")) && (!temp.equals("D"))
                    && (!temp.equals("E")) && (!temp.equals("F")) && (!temp.equals("G")) && (!temp.equals("H"))
                    && (!temp.equals("I")) && (!temp.equals("J")) && (!temp.equals("K")) && (!temp.equals("L"))
                    && (!temp.equals("M")) && (!temp.equals("N")) && (!temp.equals("O")) && (!temp.equals("P"))
                    && (!temp.equals("Q")) && (!temp.equals("R")) && (!temp.equals("S")) && (!temp.equals("T"))
                    && (!temp.equals("U")) && (!temp.equals("V")) && (!temp.equals("X")) && (!temp.equals("Z"))
                    && (!temp.equals("W")) && (!temp.equals("Y"))) {
                valor = false;

            }

        }
        return valor;
    }
    
}
