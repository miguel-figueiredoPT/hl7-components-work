package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.Address;

public interface RandomPicker {
    String getFirstName();
    String getLastName();
    char getSex();
    Address getAddress();
    String getExamCode();
}
