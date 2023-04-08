package org.api.fake.util;


import org.api.fake.entity.cart.Cart;
import org.api.fake.entity.cart.PickedProduct;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

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

    public static Cart getHighestValCart(InitData data) {
        BigDecimal highestVal = ZERO;
        Cart highestValCart = null;

        for (Cart cart : data.getCarts()) {
            BigDecimal currentCartVal = calculateCartValue(cart, data);
            if (highestVal.compareTo(currentCartVal) < 0) {
                highestVal = currentCartVal;
                highestValCart = cart;
            }
        }
        return highestValCart;
    }

    public static BigDecimal calculateCartValue(Cart cart, InitData data) {
        BigDecimal valToCalculate = ZERO;

        for (PickedProduct pickedProduct : cart.getProducts()) {
            int pickedProdIdInList = pickedProduct.getProductId() - 1;
            int pickedProdQuantity = pickedProduct.getQuantity();
            valToCalculate = valToCalculate
                    .add(data.getProducts().get(pickedProdIdInList).getPrice()
                            .multiply(new BigDecimal(pickedProdQuantity)));
        }

        return valToCalculate;
    }
    public static String getCartOwnerAndVal(Cart cart, InitData data) {
        int ownerIdInList = cart.getUserId() - 1;
        return data.getUsers().get(ownerIdInList).getName() + " " + calculateCartValue(cart, data).toString();
    }
}
