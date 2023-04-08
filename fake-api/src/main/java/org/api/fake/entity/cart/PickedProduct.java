package org.api.fake.entity.cart;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PickedProduct {
    private int productId;
    private int quantity;
}
