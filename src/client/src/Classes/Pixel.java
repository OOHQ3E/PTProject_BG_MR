package Classes;

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
        this.x = x;
    }

    private int y;
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    private int r;
    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }

    private int g;
    public int getG() {
        return g;
    }
    public void setG(int g) {
        this.g = g;
    }

    private int b;
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Pixel;" + x + ";" + y + ";" + r + ";" + g + ";" + b;
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

