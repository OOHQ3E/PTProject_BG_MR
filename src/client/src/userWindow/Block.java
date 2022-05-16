package userWindow;

import java.awt.*;

public class Block {
    public static int blockSize = 20;
    private int x,y;
    private Color color;
    public Block(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void draw(Graphics graphics){
        graphics.setColor(color);
        graphics.fillRect((x*blockSize)+1, (y*blockSize)+1, blockSize-1,blockSize-1);
    }
    public void setColor(Color color){
       this.color = color;
    }
}
