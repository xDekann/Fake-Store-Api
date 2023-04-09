package org.api.fake;

import org.api.fake.util.InitData;
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
            try {
                System.out.println("---- Task 2 (task 1 succeeded) ----");
                Map<String, BigDecimal> sumOfCategories = TaskUtil.getSumOfCategories(data);
                sumOfCategories.forEach((key, value) -> System.out.println(key + " " + value));
                System.out.println("---- Task 3 ----");
                System.out.println(TaskUtil.getCartOwnerAndVal(TaskUtil.getHighestValCart(data), data));
                System.out.println("---- Task 4 ----");
                TaskUtil.getTwoMostDistancedUsers(data).forEach(System.out::println);
            } catch (NullPointerException nullPointerException) {
                nullPointerException.printStackTrace();
            }
        } catch (IOException ioException) {
            System.out.println("Reading of API has failed");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
