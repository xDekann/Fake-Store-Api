package org.api.fake.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaskUtil {
    public static Map<String, BigDecimal> getSumOfCategories(InitData initData) {
        Map<String, BigDecimal> result = new HashMap<>();
        initData.getProducts().forEach(product -> {
            if(!result.containsKey(product.getCategory()))
                result.put(product.getCategory(), product.getPrice());
            else {
                BigDecimal currentPriceInMap = result.get(product.getCategory());
                result.replace(product.getCategory(), currentPriceInMap, currentPriceInMap.add(product.getPrice()));
            }
        });
        return result;
    }
}
