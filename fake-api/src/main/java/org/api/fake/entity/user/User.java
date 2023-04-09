package org.api.fake.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Address address;
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;

    @Override
    public String toString() {
        return "User{" +
                "address=" + address +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", name=" + name +
                ", phone='" + phone + '\'' +
                '}';
    }
}
