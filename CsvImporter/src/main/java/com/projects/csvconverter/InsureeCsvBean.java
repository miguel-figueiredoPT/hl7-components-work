package com.projects.csvconverter;

import com.opencsv.bean.CsvBindByName;

public class InsureeCsvBean {

    @CsvBindByName(column = "userId")
    private String userId;

    @CsvBindByName(column = "firstName")
    private String firstName;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "version")
    private int version;

    @CsvBindByName(column = "insuranceCompany")
    private String insuranceCompany;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getVersion() {
        return version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + insuranceCompany.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        InsureeCsvBean u=(InsureeCsvBean) obj;

        return u.userId.equals(userId) && u.insuranceCompany.equals(insuranceCompany);
    }


}