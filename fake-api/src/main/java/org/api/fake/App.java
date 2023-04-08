package org.api.fake;

import org.api.fake.util.InitData;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
        InitData data = new InitData();

        try {
            data.initializeJsonData();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
