package Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixelTest {

    @Test
    void createPixel() {
        int expectedX = 5;
        int expectedY = 6;
        int expectedR = 125;
        int expectedG = 5;
        int expectedB = 200;
        Pixel actualPixel = new Pixel(expectedX,expectedY,expectedR,expectedG,expectedB);
        assertEquals(actualPixel.getX(),expectedX);
        assertEquals(actualPixel.getY(),expectedY);
        assertEquals(actualPixel.getR(),expectedR);
        assertEquals(actualPixel.getG(),expectedG);
        assertEquals(actualPixel.getB(),expectedB);
    }

    @Test
    void ParsePixel(){
        Pixel expectedPixel = new Pixel(5,6,125,5,200);
        Pixel actual = Pixel.convertStringToPixel(expectedPixel.toString());

        assertEquals(expectedPixel,actual);
    }
}