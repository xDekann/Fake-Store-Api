package org.api.fake.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private int id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
