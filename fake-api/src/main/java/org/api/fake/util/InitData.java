package org.api.fake.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.api.fake.entity.cart.Cart;
import org.api.fake.entity.product.Product;
import org.api.fake.entity.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.api.fake.util.constant.FakeApiConst.*;

@Setter
@Getter
public class InitData {
    private List<User> users;
    private List<Product> products;
    private List<Cart> carts;

    public void initializeJsonData() throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());

        users = jsonMapper.readValue(new URL(USER_FAKE_API), new TypeReference<>() {});
        carts = jsonMapper.readValue(new URL(CART_FAKE_API), new TypeReference<>() {});
        products = jsonMapper.readValue(new URL(PRODUCT_FAKE_API), new TypeReference<>() {});
    }
}
