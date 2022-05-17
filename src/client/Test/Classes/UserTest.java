package Classes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUser() {
        int expectedId = 3;
        String expectedUsername = "name";
        int expectedAuthLevel = 3;
        User actualuser = new User(expectedId,expectedUsername,expectedAuthLevel);
        assertEquals(actualuser.getId(),expectedId);
        assertEquals(actualuser.getUserName(),expectedUsername);
        assertEquals(actualuser.getAuthLevel(),expectedAuthLevel);
    }
    @Test
    void ParseUser(){
        User expectedUser = new User(1,"expectedname",3);
        User actual = User.convertStringToUser(expectedUser.toString());

        assertEquals(expectedUser,actual);
    }

    @Test
    void ListUsers(){
        ArrayList<User> expectedUsers = new ArrayList<>();

        User user1 = new User(1,"expectedname1",3);
        User user2 = new User(2,"expectedname2",3);
        User user3 = new User(3,"expectedname3",3);
        expectedUsers.add(user1);
        expectedUsers.add(user2);
        expectedUsers.add(user3);

        String listString = "UserList:"+user1.toString()+":"+user2.toString()+":"+user3.toString();
        ArrayList<User> actualUsers = User.convertStringToUserList(listString);

        assertEquals(expectedUsers,actualUsers);
    }
}