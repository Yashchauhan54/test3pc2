package org.example.test3yashpc2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTestyash {
    @Test
    void testtheirtotal(){
        assertEquals(HelloController.calculateTotal("XL", "1"),17.975);
    }

}


