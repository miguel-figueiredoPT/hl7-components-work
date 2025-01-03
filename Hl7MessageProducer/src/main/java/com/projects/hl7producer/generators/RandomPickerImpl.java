package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component
public class RandomPickerImpl implements RandomPicker {
    private final RandomGenerator r;

    public RandomPickerImpl(@Autowired RandomGenerator r) {
        this.r = r;
    }

    String[] FIRST_NAMES = {"Adam", "Alex", "George"};
    String[] LAST_NAMES = {"Smith", "Jones", "Hendrix"};

    String[] STREET_TYPES = {"St", "Square"};
    String[] STREET_NAMES = {"Milky Way", "Kings"};

    char[] SEXES = {'F','M','O','U'};

    String[] CITIES = {"Washington", "New York"};

    String[] COUNTRIES = {"US", "UK"};


    String[] EXAM_CODES = {"CT","XR","DX", "MR"};


    public String getFirstName() {
        return randomString(FIRST_NAMES);
    }

    public String getLastName() {
        return randomString(LAST_NAMES);
    }

    public char getSex() { return SEXES[r.nextInt(SEXES.length)];}

    public Address getAddress() {
        return new Address(
                randomString(STREET_TYPES) + " " + randomString(STREET_NAMES),
                r.nextInt(5000),
                r.nextInt(10000),
                randomString(CITIES),
                randomString(COUNTRIES)
        );
    }

    public String getExamCode() {
        return randomString(EXAM_CODES);
    }

    private String randomString(String[] array) {
        return array[r.nextInt(array.length)];
    }
}
