package org.api.fake.entity.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    @JsonAlias("firstname")
    private String firstName;
    @JsonAlias("lastname")
    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
