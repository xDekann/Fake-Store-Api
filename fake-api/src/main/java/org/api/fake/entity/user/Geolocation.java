package org.api.fake.entity.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Geolocation {
    @JsonAlias("lat")
    private BigDecimal latitude;
    @JsonAlias("long")
    private BigDecimal longitude;
}
