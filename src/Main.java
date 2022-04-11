import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Java F1"); // Создаём объект класса jFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Кнопки закрыть окно
        f.setSize(1200, 840); // Размеры окна
        f.add(new Road()); // Добавляем на JFrame обект класса Road
        f.setVisible(true); // Значение видимости окна


    }
}
