/*Este es un cambio hecho por esau para probar github*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Snake extends JFrame implements KeyListener, Runnable {

    JPanel panel;
    JButton[] Snake = new JButton[200];
    JButton comida;
    /* VARIABLES INT */
    int x = 500; 
    int y = 300; 
    int cont = 2; 
    int dirx = 1; 
    int diry = 0; 
    int vel = 50; 
    int dif = 0; 
    int oldx; 
    int oldy;
    /* ARREGLOS INT */
    int[] arrx = new int[300];
    int[] arry = new int[300];
    /* ARREGLO POINT  */
    Point[] arrp = new Point[300];
    
    Point cp = new Point();
    /* HILO */
    Thread hilo;
    /* VARIABLES BOOLEAN */
    boolean bc = false;
    boolean izq = false;
    boolean der = true;
    boolean y1 = true;
    boolean y2 = true;
    /* RANDOM */
    Random r = new Random();
    

    Snake() {
        
        super("Snake");
        setSize(500, 330);
        cont = 3;
        arrx[0] = 100;
        arry[0] = 150;
        dirx = 10;
        diry = 0;
        dif = 0;
        
        bc = false;
        izq = false;
        der = true;
        y1 = true;
        y2 = true;
        
        
        panel = new JPanel();
        
        comida = new JButton();
        comida.setEnabled(false);
        
        Snakeprincipal();

        panel.setLayout(null);
        
        panel.setBounds(0, 0, x, y);
        panel.setBackground(Color.black);
        
        getContentPane().setLayout(null);
        getContentPane().add(panel);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(this);
        
        hilo = new Thread(this);
        hilo.start(); 
    }

    public void Snakeprincipal() {
        // Initially the snake has small length 3
        for (int i = 0; i < 3; i++) {
            
            Snake[i] = new JButton("lb" + i);
            Snake[i].setBackground(Color.green);
            Snake[i].setEnabled(false);
            panel.add(Snake[i]);
            Snake[i].setBounds(arrx[i], arry[i], 10, 10);
            arrx[i + 1] = arrx[i] - 10;
            arry[i + 1] = arry[i]; 
        }
    }

    

    void crecer() {
        Snake[cont] = new JButton();
        Snake[cont].setEnabled(false);
        panel.add(Snake[cont]);

        int a = 10 + (10 * r.nextInt(48));
        int b = 10 + (10 * r.nextInt(23));

        arrx[cont] = a;
        arry[cont] = b;
        Snake[cont].setBounds(a, b, 10, 10);
        Snake[cont].setBackground(Color.green);

        cont++;
    }
    
    void Avanzar() {
        
        for (int i = 0; i < cont; i++) {
            arrp[i] = Snake[i].getLocation();
        }

        arrx[0] += dirx;
        arry[0] += diry;
        Snake[0].setBounds(arrx[0], arry[0], 10, 10);

        for (int i = 1; i < cont; i++) {
            Snake[i].setLocation(arrp[i - 1]);
        }

        if (arrx[0] == x) {
            arrx[0] = 10;
        } else if (arrx[0] == 0) {
            arrx[0] = x - 10;
        } else if (arry[0] == y) {
            arry[0] = 10;
        } else if (arry[0] == 0) {
            arry[0] = y - 10;
        }

        if (arrx[0] == arrx[cont - 1] && arry[0] == arry[cont - 1]) {
            bc = false;
        }

        if (bc == false) {
            crecer();
            bc = true;
        } else {
            Snake[cont - 1].setBounds(arrx[cont - 1], arry[cont - 1], 10, 10);
        }

        panel.repaint();
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        // snake move to left when player pressed left arrow
        if (izq == true && e.getKeyCode() == 37) {
            dirx = -10; // means snake move right to left by 10pixels
            diry = 0;
            der = false;     // run right(runr) means snake cant move from left to right
            y1 = true;      // run up   (runu) means snake can move from down to up
            y2 = true;      // run down (run down) means snake can move from up to down
        }
        // snake move to up when player pressed up arrow
        if (y1 == true && e.getKeyCode() == 38) {
            dirx = 0;
            diry = -10; // means snake move from down to up by 10 pixel
            y2 = false;     // run down (run down) means snake can move from up to down
            der = true;      // run right(runr) means snake can move from left to right
            izq = true;      // run left (runl) means snake can move from right to left
        }
        // snake move to right when player pressed right arrow
        if (der == true && e.getKeyCode() == 39) {
            dirx = +10; // means snake move from left to right by 10 pixel
            diry = 0;
            izq = false;
            y1 = true;
            y2 = true;
        }
        // snake move to down when player pressed down arrow
        if (y2 == true && e.getKeyCode() == 40) {
            dirx = 0;
            diry = +10; // means snake move from left to right by 10 pixel
            y1 = false;
            der = true;
            izq = true;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        int i=0;
        while(i<1) {
            
            Avanzar();
            try {
                Thread.sleep(vel);
            } catch (InterruptedException ie) {
            }
        }
    }
}
