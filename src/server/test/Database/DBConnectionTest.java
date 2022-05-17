package Database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    @Test
    void dbUsers() {
        DBConnection dbConnection = new DBConnection("localhost", "progtech", "progtech", "");
        String name = "newTestUser";
        String password = "testUserPassword";
        int authLevel = 1;
        // creating new user
        User expectedU = new User(0, name, authLevel);
        dbConnection.AddNewUser(name, password, authLevel);

        // getting the user from database
        User actualU = dbConnection.UserExists(name, password);
        assertEquals(actualU.getUserName(), expectedU.getUserName());
        assertEquals(actualU.getAuthLevel(), expectedU.getAuthLevel());

        // other way getting the user from database
        int id = actualU.getId();
        actualU = dbConnection.GetUser(id);
        assertEquals(actualU.getUserName(), expectedU.getUserName());
        assertEquals(actualU.getAuthLevel(), expectedU.getAuthLevel());

        //updating user
        String newName = "newName";
        String newPassword = "newPassword";
        int newAuthLevel = 3;
        expectedU.setUserName(newName);
        expectedU.setAuthLevel(newAuthLevel);
        dbConnection.UpdateUser(id, newName, newPassword, newAuthLevel);
        actualU = dbConnection.GetUser(id);
        assertEquals(actualU.getUserName(), expectedU.getUserName());
        assertEquals(actualU.getAuthLevel(), expectedU.getAuthLevel());

        // deleting user
        dbConnection.DeleteUser(actualU.getId());
        actualU = dbConnection.UserExists(newName, newPassword);
        assertNull(actualU);
    }

    @Test
    void dbPixel() {
        DBConnection dbConnection = new DBConnection("localhost", "progtech", "progtech", "");
        int width = 15;

        dbConnection.ResetPixelCanvas(width);

        ArrayList<Pixel> pixels = dbConnection.GetAllPixels();
        assertEquals(pixels.size(), width * width);

        int x = 5;
        int y = 6;
        int r = 200;
        int g = 100;
        int b = 50;
        int Index = x * width + y;
        Pixel expectedPixel = new Pixel(x,y,r,g,b);
        dbConnection.UpdatePixel(x, y, r, g, b);
        pixels = dbConnection.GetAllPixels();
        Pixel actualPixel = pixels.get(Index);

        assertEquals(actualPixel, expectedPixel);

    }


}