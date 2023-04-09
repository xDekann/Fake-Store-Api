package org.api.fake.util;

import org.api.fake.entity.cart.Cart;
import org.api.fake.entity.cart.PickedProduct;
import org.api.fake.entity.product.Product;
import org.api.fake.entity.user.Address;
import org.api.fake.entity.user.Geolocation;
import org.api.fake.entity.user.Name;
import org.api.fake.entity.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskUtilTest {

    private InitData initData;

    @BeforeAll
    public void initExampleData() {
        List<Product> productList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        initData = new InitData();

        productList.add(Product.builder()
                .id(1)
                .price(new BigDecimal("210.80"))
                .category("A")
                .build());
        productList.add(Product.builder()
                .id(2)
                .price(new BigDecimal("159.00"))
                .category("B")
                .build());
        productList.add(Product.builder()
                .id(3)
                .price(new BigDecimal("31.22"))
                .category("A")
                .build());

        userList.add(User.builder() // Shanghai
                .id(1)
                .address(Address.builder()
                        .geolocation(new Geolocation(31.224361, 121.469170))
                        .build())
                .name(new Name("USER1", "USER1L"))
                .build());
        userList.add(User.builder() // Cracow
                .id(2)
                .address(Address.builder()
                        .geolocation(new Geolocation(50.0646501, 19.9449799))
                        .build())
                .name(new Name("USER2", "USER2L"))
                .build());
        userList.add(User.builder() // Berlin
                .id(3)
                .address(Address.builder()
                        .geolocation(new Geolocation(12.0288, 17.8344))
                        .build())
                .name(new Name("USER3", "USER3L"))
                .build());

        List<PickedProduct> pickedProductsUser1 =
                List.of(new PickedProduct(1,2), new PickedProduct(2,3));
        List<PickedProduct> pickedProductsUser2 =
                List.of(new PickedProduct(2,2), new PickedProduct(3,4));
        List<PickedProduct> pickedProductsUser3 =
                List.of(new PickedProduct(3,1), new PickedProduct(1,1));

        cartList.add(Cart.builder()
                .id(1)
                .userId(1)
                .products(pickedProductsUser1)
                .build());
        cartList.add(Cart.builder()
                .id(2)
                .userId(2)
                .products(pickedProductsUser2)
                .build());
        cartList.add(Cart.builder()
                .id(3)
                .userId(3)
                .products(pickedProductsUser3)
                .build());

        initData.setCarts(cartList);
        initData.setUsers(userList);
        initData.setProducts(productList);
    }

    @Test
    public void testSumOfCategories() {
        // given
        Map<String, BigDecimal> givenExample = new HashMap<>();
        givenExample.put("A", new BigDecimal("242.02"));
        givenExample.put("B", new BigDecimal("159.00"));
        // when
        Map<String, BigDecimal> resultToTest = TaskUtil.getSumOfCategories(initData);
        // then
        resultToTest.forEach((key, value) -> assertEquals(givenExample.get(key), value));
    }

    @Test
    public void testGetHighestValCart() {
        // given & when
        Cart cartResult = TaskUtil.getHighestValCart(initData);
        // then
        assertEquals(cartResult, initData.getCarts().get(0));
    }

    @Test
    public void testGetCartOwnerAndVal() {
        // given & when
        String pickedResult = TaskUtil.getCartOwnerAndVal(initData.getCarts().get(0), initData);
        // then
        assertEquals(pickedResult, "USER1 USER1L 898.60");
    }

    @Test
    public void testHaversineDist() {
        // given
        Geolocation geo1 = initData.getUsers().get(1).getAddress().getGeolocation();
        Geolocation geo2 = initData.getUsers().get(2).getAddress().getGeolocation();
        // when
        double dist =
                TaskUtil.haversineDistBetweenPeople(geo1.getLatitude(), geo2.getLatitude(),
                        geo1.getLongitude(), geo2.getLongitude());
        // then
        assertTrue(dist > 4200);
        assertTrue(dist < 4300);
    }

    @Test
    public void testGetTwoMostDistancedUsers() {
        // given & when
        List<User> userResult = TaskUtil.getTwoMostDistancedUsers(initData);
        userResult.forEach(System.out::println);
        // then
        assertTrue(userResult.contains(initData.getUsers().get(0)));
        assertTrue(userResult.contains(initData.getUsers().get(2)));
    }
}
