package userWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Draw extends JPanel {
    private static int blockSize = 15;
    public static Block[][] blocks = new Block[blockSize][blockSize];
    public static Color[][] pixels = new Color[blockSize][blockSize];

    public Draw(){
        paletteRedraw();
    }
    public static void paletteRedraw(){
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {

                if (pixels[i][j] == null){
                    blocks[i][j] = new Block(j,i,Color.lightGray);
                }
                else {
                    blocks[i][j] = new Block(j,i,pixels[i][j]);
                    //System.out.println(blocks[i][j]);
                    System.out.println("stepped in else statement " +  pixels[i][j]);
                }
            }
        }
        System.out.println("i drew it the canvas " +  pixels[1][1]);
    }

    //public void SwitchState(int x, int y)
    //{
   //     pixels[x][y] = !pixels[x][y];
   // }


    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j].draw(g);
            }
        }
    }


}
