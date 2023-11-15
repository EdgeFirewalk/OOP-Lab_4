import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Создаём и настраиваем окно
        JFrame fr = new JFrame("Вращение четырёхугольника вокруг центра тяжести");
        fr.setPreferredSize(new Dimension(300,300));

        // Создаём панель, куда будет добавляться графика
        final JPanel pan = new JPanel();

        // Добавляем панель в окно
        fr.add(pan);
        fr.setVisible(true); // Делаем панель видимой
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Правильно завершаем приложение, когда окно закрыто
        fr.pack();

        // Создаём таймер для вращения четырёхугольника
        Timer tm = new Timer(100, new ActionListener() {
            int i = 0; // Подсчитываем количество кадров с начала таймера

            public void actionPerformed(ActionEvent arg0) {
                // Получаем доступ к графике JPanel
                Graphics2D gr = (Graphics2D)pan.getRootPane().getGraphics();
                // Обновляем панель
                pan.update(gr);

                // Создаём объект, рисующий "пути" между указанными точками
                GeneralPath path = new GeneralPath();
                // Добавляем точки на объект, указываем, что их нужно соединить
                path.append(new Polygon(new int []{ 5, 80, 70, 15 }, new int[]{ 5, 20, 90, 50 },4),true);

                // Высчитываем центр тяжести четырёхугольника
                int x = (5 + 80 + 70 + 15) / 4;
                int y = (5 + 20 + 90 + 50) / 4;

                /*
                // Тесты с квадратом
                path.append(new Polygon(new int []{ 20, 60, 60, 20 }, new int[]{ 20, 20, 60, 60 },4),true);
                int x = (20 + 60 + 60 + 20) / 4;
                int y = (20 + 20 + 60 + 60) / 4;
                */

                // Двигаем четырёхугольник ближе к центру окна
                gr.translate(fr.getWidth() / 2 - x, fr.getHeight() / 2 - y);

                // Создаём AffineTransform, который вращает что-либо
                AffineTransform transforms = AffineTransform.getRotateInstance((i++) * 0.08, x, y);
                // Применяем вращение к четырёхугольнику
                gr.transform(transforms);
                // Отрисовываем четырёхугольник
                gr.draw(path);
            }
        });

        // Начинаем работу таймера
        tm.start();
    }
}