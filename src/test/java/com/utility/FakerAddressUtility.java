package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPojo;

import java.util.Locale;

public class FakerAddressUtility {

    public static AddressPojo getFakerAddress(){

        Faker faker = new Faker(Locale.US);
        return new AddressPojo(faker.company().name(),faker.address().buildingNumber(), faker.address().streetAddress(),
                faker.address().city(),faker.numerify("#####"), faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(),
                "other","office address", faker.address().state());
    }
}
