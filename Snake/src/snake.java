/*Este es un cambio hecho por esau para probar github*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Snake extends JFrame implements KeyListener, Runnable {

    JPanel panel; //Panel donde se despliega el juego
    
    JButton[] SnakeP1 = new JButton[200];
    JButton[] SnakeP2 = new JButton[200]; /*Player2*/
    
    /* VARIABLES INT */
    int x = 500;  //Tamaño del panel en X
    int y = 300;  //Tamaño del panel en Y
    
    int contP1 = 2; 
    int contP2 = 2; /*CONTADOR PLAYER2*/
    
    int dirx = 1; 
    int diry = 0; 
    int dirxP2 = 1; 
    int diryP2 = 0; 
    
    int vel = 70; 
    int velP2 = 70; /*VELOCIDAD PLAYER2*/
    
    //int dif = 0; 
    //int oldx; 
    //int oldy;
    
    /* ARREGLOS INT */
    int[] arrx = new int[300];
    int[] arry = new int[300];
    int[] arrxP2 = new int[300];
    int[] arryP2 = new int[300];
    /* ARREGLO POINT  */
    Point[] arrp = new Point[300];
    Point[] arrpP2 = new Point[300];
    //Point cp = new Point();
    /* HILO */
    Thread hilo;
    /* VARIABLES BOOLEAN */
    boolean bc = false;
    boolean izq = false;
    boolean der = true;
    boolean y1 = true;
    boolean y2 = true;
    /* VARIABLES BOOLEAN P2*/
    boolean bcP2 = false;
    boolean izqP2 = false;
    boolean derP2 = true;
    boolean y1P2 = true;
    boolean y2P2 = true;
    /* RANDOM */
    Random r = new Random();
    
/******** CONSTRUCTOR ****************/
    Snake() {
        
        super("Snake");
        setSize(500, 330);
        
        contP1 = 3;
        contP2 = 3;
        
        arrx[0] = 100;
        arry[0] = 150;
        arrxP2[0] = 100;
        arryP2[0] = 150;
        
        dirx = 10;
        diry = 0;
        dirxP2 = 10;
        diryP2 = 0;
        //dif = 0;
        
        bc = false;
        izq = false;
        der = true;
        y1 = true;
        y2 = true;
        
        bcP2 = false;
        izqP2 = false;
        derP2 = true;
        y1P2 = true;
        y2P2 = true;
        
        
        panel = new JPanel();        
        
        Snakeprincipal();
        SnakeprincipalP2();
        
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
    }/****fin de constructor******/

    public void Snakeprincipal() {
        // Initially the snake has small length 3
        for (int i = 0; i < 3; i++) {
            
            SnakeP1[i] = new JButton("lb" + i);
            SnakeP1[i].setBackground(Color.green);
            SnakeP1[i].setEnabled(false);
            panel.add(SnakeP1[i]);
            
            SnakeP1[i].setBounds(arrx[i], arry[i], 10, 10);
            
            arrx[i + 1] = arrx[i] - 10;
            arry[i + 1] = arry[i]; 
        }
    }
    public void SnakeprincipalP2() { /*Metodo para player 2*/
        // Initially the snake 2 has small length 3
        for (int i = 0; i < 3; i++) {
            
            SnakeP2[i] = new JButton("lb" + i);
            SnakeP2[i].setBackground(Color.red);
            SnakeP2[i].setEnabled(false);
            panel.add(SnakeP2[i]);
            
            SnakeP2[i].setBounds(arrxP2[i], arryP2[i], 10, 10);
            
            arrxP2[i + 1] = arrxP2[i] - 10;
            arryP2[i + 1] = arryP2[i]; 
        }
    }

    

    void crecer() {
        SnakeP1[contP1] = new JButton();
        SnakeP1[contP1].setEnabled(false);
        panel.add(SnakeP1[contP1]);

        int a = 10 + (10 * r.nextInt(48));
        int b = 10 + (10 * r.nextInt(23));

        arrx[contP1] = a;
        arry[contP1] = b;
        
        SnakeP1[contP1].setBounds(a, b, 10, 10);
        SnakeP1[contP1].setBackground(Color.green);

        contP1++;
    }
    
    void crecerP2() { /*Metodo para player 2*/
        SnakeP2[contP2] = new JButton();
        SnakeP2[contP2].setEnabled(false);
        panel.add(SnakeP2[contP2]);

        int a = 10 + (10 * r.nextInt(48));
        int b = 10 + (10 * r.nextInt(23));

        arrxP2[contP2] = a;
        arryP2[contP2] = b;
        
        SnakeP2[contP2].setBounds(a, b, 10, 10);
        SnakeP2[contP2].setBackground(Color.red);

        contP2++;
    }
    
    void Avanzar() {
        
        for (int i = 0; i < contP1; i++) {
            arrp[i] = SnakeP1[i].getLocation();
        }

        arrx[0] += dirx;
        arry[0] += diry;
        SnakeP1[0].setBounds(arrx[0], arry[0], 10, 10);

        for (int i = 1; i < contP1; i++) {
            SnakeP1[i].setLocation(arrp[i - 1]);
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

        if (arrx[0] == arrx[contP1 - 1] && arry[0] == arry[contP1 - 1]) {
            bc = false;
        }

        if (bc == false) {
            crecer();
            bc = true;
        } else {
            SnakeP1[contP1 - 1].setBounds(arrx[contP1 - 1], arry[contP1 - 1], 10, 10);
        }

        panel.repaint();
        setVisible(true);
    }
    /********************************************************/
    
    void AvanzarP2() {
        
        for (int i = 0; i < contP2; i++) {
            arrpP2[i] = SnakeP2[i].getLocation();
        }

        arrxP2[0] += dirxP2;
        arryP2[0] += diryP2;
        SnakeP2[0].setBounds(arrxP2[0], arryP2[0], 10, 10);

        for (int i = 1; i < contP2; i++) {
            SnakeP2[i].setLocation(arrpP2[i - 1]);
        }

        if (arrxP2[0] == x) {
            arrxP2[0] = 10;
        } else if (arrxP2[0] == 0) {
            arrxP2[0] = x - 10;
        } else if (arryP2[0] == y) {
            arryP2[0] = 10;
        } else if (arryP2[0] == 0) {
            arryP2[0] = y - 10;
        }

        if (arrxP2[0] == arrxP2[contP2 - 1] && arryP2[0] == arryP2[contP2 - 1]) {
            bcP2 = false;
        }

        if (bcP2 == false) {
            crecerP2();
            bcP2 = true;
        } else {
            SnakeP2[contP2 - 1].setBounds(arrxP2[contP2 - 1], arryP2[contP2 - 1], 10, 10);
        }

        panel.repaint();
        setVisible(true);
    }
    
    /******************************************************/
    @Override
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
        
        /* ******* Desiciones para el Player dos *************/
        
        if (izqP2 == true && e.getKeyCode() == 49) {
            dirxP2 = -10; // means snake move right to left by 10pixels
            diryP2 = 0;
            derP2 = false;     // run right(runr) means snake cant move from left to right
            y1P2 = true;      // run up   (runu) means snake can move from down to up
            y2P2 = true;      // run down (run down) means snake can move from up to down
        }
        // snake move to up when player pressed up arrow
        if (y1P2 == true && e.getKeyCode() == 50) {
            dirxP2 = 0;
            diryP2 = -10; // means snake move from down to up by 10 pixel
            y2P2 = false;     // run down (run down) means snake can move from up to down
            derP2 = true;      // run right(runr) means snake can move from left to right
            izqP2 = true;      // run left (runl) means snake can move from right to left
        }
        // snake move to right when player pressed right arrow
        if (derP2 == true && e.getKeyCode() == 51) {
            dirxP2 = +10; // means snake move from left to right by 10 pixel
            diryP2 = 0;
            izqP2 = false;
            y1P2 = true;
            y2P2 = true;
        }
        
        // snake move to down when player pressed down arrow
        if (y2P2 == true && e.getKeyCode() == 52) {
            dirxP2 = 0;
            diryP2 = +10; // means snake move from left to right by 10 pixel
            y1P2 = false;
            derP2 = true;
            izqP2 = true;
        }
        /**********Fin de decisiones player dos ***********/
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        int i=0;
        while(i<1) {
            
            Avanzar();
            AvanzarP2();
            try {
                Thread.sleep(vel);
            } catch (InterruptedException ie) {
            }
        }
    }
}
