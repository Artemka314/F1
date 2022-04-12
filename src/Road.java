import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import static java.awt.font.TextAttribute.FONT;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, this); // Создаём таймер

    Image img = new ImageIcon("res/bg_road.png").getImage(); // Добавляем картинку
    Player p = new Player();

    Thread enemiesFactory = new Thread(this);
    List<Enemy> enemies = new ArrayList<Enemy>();


    public Road() {
        mainTimer.start(); // Запускаем таймер
        enemiesFactory.start();
        addKeyListener(new myKeyAdapter());
        setFocusable(true);

    }


    private class myKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            p.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);

        }
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null); //Метод рисования, нарисовали картинку и кординатами 0,0
        g.drawImage(img, p.layer2, 0, null); //Метод рисования, нарисовали картинку и кординатами 1200 по х
        g.drawImage(p.img, p.x, p.y, null); // Нарисовали картинку машины

        double v = (200 / p.MAX_V) * p.v;
        g.setColor(Color.white);
        Font Font = new Font("Arial",3,20);
        g.setFont(Font);
        g.drawString("Скорость: " + v + "км/ч", 100, 80);


        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();
                g.drawImage(e.img, e.x, e.y, null);
            }
        }


    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemy();
    }

    private void testCollisionWithEnemy() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                i.remove();
                JOptionPane.showMessageDialog(null, "Вы Проиграли");
                System.exit(1);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200,
                        rand.nextInt(500),
                        rand.nextInt(80),
                        this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
