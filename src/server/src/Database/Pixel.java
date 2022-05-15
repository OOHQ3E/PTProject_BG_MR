package Database;

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
}
