package org.api.fake.entity.product;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private double rate;
    private int count;
}
