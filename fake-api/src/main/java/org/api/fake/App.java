package org.api.fake;

import org.api.fake.util.InitData;
import java.io.IOException;
import org.api.fake.util.TaskUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class App
{
    public static void main( String[] args ) {
        InitData data = new InitData();

        try {
            data.initializeJsonData();
            Map<String, BigDecimal> sumOfCategories = TaskUtil.getSumOfCategories(data);
            sumOfCategories.entrySet().forEach(record -> System.out.println(record.getKey() + " " + record.getValue()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
