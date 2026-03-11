package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class DemoAppTest {
    @Test
    public void testGreeting() {
        DemoApp app = new DemoApp();
        assertEquals("Hello from Demo App!", app.getGreeting());
    }

    @Test
    public void testAppNotNull() {
        DemoApp app = new DemoApp();
        assertNotNull(app);
    }
}

