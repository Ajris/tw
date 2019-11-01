package task1;

import java.util.ArrayList;
import java.util.List;

import static task1.Const.MAX_ITER;
import static task1.Const.WIDTH;
import static task1.Const.ZOOM;

class Task {
    private int from;
    private int to;

    Task(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public List<Point> calculate() {double zx, zy, cX, cY, tmp;
        List<Point> points = new ArrayList<>();

        for (int i = from; i < to; i++) {
            int y = i / WIDTH;
            int x = i % WIDTH;

            zx = zy = 0;
            cX = (x - 400) / ZOOM;
            cY = (y - 300) / ZOOM;
            int iter = MAX_ITER;
            while (zx * zx + zy * zy < 4 && iter > 0) {
                tmp = zx * zx - zy * zy + cX;
                zy = 2.0 * zx * zy + cY;
                zx = tmp;
                iter--;
            }
            points.add(new Point(x, y, iter | (iter << 8)));
        }

        return points;
    }
}
