package task1;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Mandelbrot extends JFrame {
    private BufferedImage I;
    private long time;

    Mandelbrot(int taskCount, ExecutorService executorService) {
        super("Mandelbrot Set");
        setBounds(100, 100, Const.WIDTH, Const.HEIGHT);
        setResizable(false);
        I = new BufferedImage(Const.WIDTH, getHeight(), BufferedImage.TYPE_INT_RGB);

        int pixelNumber = Const.WIDTH * Const.HEIGHT;
        int pixelCountPerTask = pixelNumber / taskCount;

        List<CompletableFuture<List<Point>>> allPoints = new ArrayList<>();

        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < taskCount; i++) {
            int from = pixelCountPerTask * i;
            int to = Math.min(pixelCountPerTask * (i + 1), Const.WIDTH * Const.HEIGHT);

            Task task = new Task(from, to);
            allPoints.add(CompletableFuture.supplyAsync(task::calculate, executorService));
        }

        allPoints.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .forEach(p -> I.setRGB(p.getX(), p.getY(), p.getRgb()));

        long endTime = System.currentTimeMillis();

        time = endTime - currentTime;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public long getTime() {
        return time;
    }
}