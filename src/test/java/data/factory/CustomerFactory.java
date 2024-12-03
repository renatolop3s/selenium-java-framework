package data.factory;

import com.github.javafaker.Faker;
import data.model.Customer;
import lombok.extern.slf4j.Slf4j;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Slf4j
public class CustomerFactory {

    private static final Faker FAKER = new Faker();

    public static Customer create() {
        return Customer.builder()
                .username(cfg().getProperty("username"))
                .password(cfg().getProperty("password"))
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .postalCode(FAKER.address().zipCode())
                .build();
    }
}
