package org.api.fake.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InitDataTest {

    @Test
    public void testForInit() {
        InitData data = new InitData();
        Assertions.assertDoesNotThrow(data::initializeJsonData);
    }
}
