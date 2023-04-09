package org.api.fake.util;

import org.api.fake.entity.cart.Cart;
import org.api.fake.entity.cart.PickedProduct;
import org.api.fake.entity.user.Geolocation;
import org.api.fake.entity.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static org.api.fake.util.constant.FakeApiConst.EARTH_RADIUS;

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
        final int BIG_DECIMAL_LESS = -1;
        BigDecimal highestVal = ZERO;
        Cart highestValCart = null;

        for (Cart cart : data.getCarts()) {
            BigDecimal currentCartVal = calculateCartValue(cart, data);
            if (highestVal.compareTo(currentCartVal) == BIG_DECIMAL_LESS) {
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

    public static double haversineDistBetweenPeople(double lat1, double lat2, double lon1, double lon2) {
        // h = hav(lat2-lat1) + cos(lat1) * cos(lat2) * hav(lon2 - lon1)
        // hav(theta) = sin^2(theta/2)
        // dist = 2 * radius * arcsin(sqrt(h))

        double radLatDiff = Math.toRadians(lat2 - lat1);
        double radLonDiff = Math.toRadians(lon2 - lon1);
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);

        double haversine = Math.pow(Math.sin(radLatDiff / 2), 2)
                + (Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(radLonDiff / 2), 2));

        return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(haversine));
    }

    public static List<User> getTwoMostDistancedUsers(InitData data) {
        final int TWO_USER_CAPACITY = 2;
        final int FIRST_USER_LIST_INDEX = 0;
        final int SECOND_USER_LIST_INDEX = 1;
        double longestDist = 0;
        List<User> twoUsers = new ArrayList<>(TWO_USER_CAPACITY);

        for (User firstUser : data.getUsers()) {
            for (User secondUser : data.getUsers()) {
                Geolocation firstLocation = firstUser.getAddress().getGeolocation();
                Geolocation secondLocation = secondUser.getAddress().getGeolocation();
                double haversineDist = haversineDistBetweenPeople(firstLocation.getLatitude(), secondLocation.getLatitude(),
                        firstLocation.getLongitude(), secondLocation.getLongitude());

                if (longestDist < haversineDist) {
                    longestDist = haversineDist;
                    if (twoUsers.isEmpty()) {
                        twoUsers.add(FIRST_USER_LIST_INDEX, firstUser);
                        twoUsers.add(SECOND_USER_LIST_INDEX, secondUser);
                    } else {
                        twoUsers.set(FIRST_USER_LIST_INDEX, firstUser);
                        twoUsers.set(SECOND_USER_LIST_INDEX, secondUser);
                    }
                }
            }
        }

        return twoUsers;
    }
}
