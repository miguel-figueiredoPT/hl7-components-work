package com.projects.csvconverter;

import java.util.Comparator;

public class InsureeCsvBeanNameComparator implements Comparator<InsureeCsvBean> {

    public int compare(InsureeCsvBean a, InsureeCsvBean b)
    {
        var result = a.getLastName().compareTo(b.getLastName());
        return result != 0 ? result : a.getFirstName().compareTo(b.getFirstName());
    }
}