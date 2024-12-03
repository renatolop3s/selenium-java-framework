package data.model;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Customer {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
