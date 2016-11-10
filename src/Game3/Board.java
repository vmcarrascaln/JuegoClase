package Game3;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    //Permite lanzar constantemente eventos de tipo ActionListener.
    private Timer timer;
    
    private Circle circle;
    private Rect rectangle;
    
    private final int DELAY = 10;
    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;

    public Board() throws Exception {
       initBoard();
    }
    
    private void initBoard() throws Exception {
        //Adiciona la clase que permite gestionar los eventos del teclado.
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
       
        //Objetos del juego.
        circle = new Circle(40,300,30,30);
        rectangle = new Rect(50, 300,30,30);
        
        timer = new Timer(DELAY, this);
        timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
        
       Graphics2D g2d = (Graphics2D) g;
       g2d.setColor(Color.RED);
       g2d.fillOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
       g2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        circle.move();
        rectangle.move();
        
        checkCollisions();
        repaint();  
    }
    
    
    public void checkCollisions() {
      Rectangle rCicle = circle.getBounds();
      Rectangle rRect = rectangle.getBounds();
      if (rCicle.intersects(rRect)) {
        System.out.println("Colision");
      }
    }
        
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            circle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            circle.keyPressed(e);
        }
    }
}