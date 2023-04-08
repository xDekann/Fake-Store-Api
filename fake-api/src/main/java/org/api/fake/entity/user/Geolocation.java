package org.api.fake.entity.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Geolocation {
    @JsonAlias("lat")
    private double latitude;
    @JsonAlias("long")
    private double longitude;
}
