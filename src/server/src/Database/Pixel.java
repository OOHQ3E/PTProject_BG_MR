package Database;

import java.util.Objects;

public class Pixel {
    public Pixel(int x, int y, int r, int g, int b) {
        this.setX(x);
        this.setY(y);
        this.setR(r);
        this.setG(g);
        this.setB(b);
    }

    private int x;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        if (x < 0) this.x = 0;
        else this.x = x;
    }

    private int y;
    public int getY() {
        return y;
    }
    public void setY(int y) {
        if (y < 0) this.y = 0;
        else this.y = y;
    }

    private int r;
    public int getR() {
        return r;
    }
    public void setR(int r) {
        if (r < 0) this.r = 0;
        else if (r > 255) this.r = 255;
        else this.r = r;
    }

    private int g;
    public int getG() {
        return g;
    }
    public void setG(int g) {
        if (g < 0) this.g = 0;
        else if (g > 255) this.g = 255;
        else this.g = g;
    }

    private int b;
    public int getB() {
        return b;
    }
    public void setB(int b) {
        if (b < 0) this.b = 0;
        else if (b > 255) this.b = 255;
        else this.b = b;
    }

    public static Pixel convertStringToPixel(String pixelString) {
        String[] data = pixelString.split(";");
        if (!Objects.equals(data[0], "Pixel")) {
            return null;
        }
        return new Pixel(
                Integer.parseInt(data[1]),
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3]),
                Integer.parseInt(data[4]),
                Integer.parseInt(data[5])
        );
    }

    @Override
    public String toString() {
        return "Pixel;" + x + ";" + y + ";" + r + ";" + g + ";" + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x && y == pixel.y && r == pixel.r && g == pixel.g && b == pixel.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, g, b);
    }
}
