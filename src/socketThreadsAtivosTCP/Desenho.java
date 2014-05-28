/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketThreadsAtivosTCP;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author DaviZonete
 */
public class Desenho {

    protected void cabeca(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(250, 50, 50, 50);
        System.out.println("desenhou cabeca");
    }

    protected void corpo(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(270, 90, 10, 100);
        System.out.println("desenhou corpo");
    }

    protected void poste(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(150, 20, 10, 200); //pillar reto vertical
        //g.setColor(Color.RED);
        g.fillRect(110, 220, 100, 10); //base do pilar
        //g.setColor(Color.BLUE);
        g.fillRect(150, 20, 120, 10); // topo do pilar
        //g.setColor(Color.DARK_GRAY);
        g.fillRect(270, 20, 10, 40);//corda
        System.out.println("desenhou poste");
    }

    protected void bracoDir(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{275, 275, 225, 225}, new int[]{105, 115, 145, 135}, 4);
        //g.drawLine(180, 160, 200, 180); 
        System.out.println("desenhou bracoDir");
    }

    protected void bracoEsq(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{275, 275, 325, 325}, new int[]{105, 115, 145, 135}, 4);
        //g.drawLine(180, 160, 200, 180);       
        System.out.println("desenhou bracoEsq");
    }

    protected void pernaDir(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{275, 275, 230, 230}, new int[]{180, 190, 210, 200}, 4);
        //g.drawLine(180, 160, 200, 180);     
        System.out.println("desenhou pernaDir");
    }

    protected void pernaEsq(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{275, 275, 320, 320}, new int[]{180, 190, 210, 200}, 4);
        //g.drawLine(180, 160, 200, 180);    
        System.out.println("desenhou pernaEsq");
    }

    public void redesenhaCinza(Graphics g) {
        g.setColor(new Color(240,240,240));
        
        g.fillOval(250, 50, 50, 50);
        g.fillRect(270, 90, 10, 100);
        g.fillPolygon(new int[]{275, 275, 320, 320}, new int[]{180, 190, 210, 200}, 4);
        g.fillPolygon(new int[]{275, 275, 230, 230}, new int[]{180, 190, 210, 200}, 4);
        g.fillPolygon(new int[]{275, 275, 325, 325}, new int[]{105, 115, 145, 135}, 4);
        g.fillPolygon(new int[]{275, 275, 225, 225}, new int[]{105, 115, 145, 135}, 4);
        //g.setColor(new Color(100,100,240));
        g.fillRect(220, 50, 200, 150);
    }
}
