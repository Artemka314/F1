import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Road extends JPanel implements ActionListener {

    Timer mainTimer = new Timer(20, this); // Создаём таймер

    Image img = new ImageIcon("res/bg_road.png").getImage(); // Добавляем картинку
    Player p = new Player();

    public Road(){
        mainTimer.start(); // Запускаем таймер
        addKeyListener(new myKeyAdapter());
        setFocusable(true);

    }

    private class myKeyAdapter  extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            p.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            p.keyReleased(e);

        }
    }

    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img,p.layer1,0,null); //Метод рисования, нарисовали картинку и кординатами 0,0
        g.drawImage(img,p.layer2,0,null); //Метод рисования, нарисовали картинку и кординатами 1200 по х
        g.drawImage(p.img,p.x,p.y,null ); // Нарисовали картинку машины


    }
    public void actionPerformed(ActionEvent e){
        p.move();
        repaint();
    }


}
