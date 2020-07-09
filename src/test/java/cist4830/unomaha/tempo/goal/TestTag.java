package cist4830.unomaha.tempo.goal;

import cist4830.unomaha.tempo.model.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTag {

    private final Tag tag = new Tag(10000L, "Test", "TestDescription", 20000L, "07/09/2020", "07/09/2020");

    @Test
    public void testGetId() {
        Assertions.assertEquals(10000L, tag.getId());
    }

    @Test
    public void testGetTag() {
        Assertions.assertEquals("Test", tag.getTag());
    }

    @Test
    public void testGetDescription() {
        Assertions.assertEquals("TestDescription", tag.getDescription());
    }

}
