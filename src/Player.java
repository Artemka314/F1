import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 80;
    public static final int MAX_TOP = 100;
    public static final int MAX_BOTTOM = 650;


    Image img_l = new ImageIcon("res/car_left.png").getImage();
    Image img_r = new ImageIcon("res/car_right.png").getImage();
    Image img_c = new ImageIcon("res/car.png").getImage();

    Image img = img_c;

    public Rectangle getRect(){
        return new Rectangle(x,y,170,60);
    }

    int v = 0; // Скорость
    int dv = 0; // Ускорение
    int s = 0; // ДЛинна пути

    int layer1 = 0; // Кордината дороги первый слой
    int layer2 = 1290;

    int x = 30; // На этих кординатах отображаеться мошина при старте
    int y = 180;
    int dy = 0;


    public void move() {
        s += v;
        v += dv;
        if (v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;

        y -= dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
        if (layer2 - v <= 0) {
            layer1 = 0;
            layer2 = 1290;

        } else {
            layer1 -= v;
            layer2 -= v;
        }


    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dv = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            dv = -1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 10;
            img = img_l;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -10;
            img = img_r;
        }


    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
            dy = 0;
            img = img_c;
        }


    }

}
