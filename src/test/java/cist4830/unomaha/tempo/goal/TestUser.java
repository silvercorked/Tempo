package cist4830.unomaha.tempo.goal;

import cist4830.unomaha.tempo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUser {

    User user = new User(10000L, "Blake", "testName", "secret", "07/12/2020", "07/13/2020");

    @Test
    public void testGetUserId() {
        Assertions.assertEquals(10000L, user.getId());
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("Blake", user.getName());
    }

    @Test
    public void testGetUsername() {
        Assertions.assertEquals("testName", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        Assertions.assertEquals("secret", user.getPassword());
    }

    @Test
    public void testGetCreatedAt() {
        Assertions.assertEquals("07/12/2020", user.getCreatedAt());
    }

    @Test
    public void testGetModifiedAt() {
        Assertions.assertEquals("07/13/2020", user.getModifiedAt());
    }
}
