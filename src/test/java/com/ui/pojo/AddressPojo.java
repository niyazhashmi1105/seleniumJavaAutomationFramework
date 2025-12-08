package com.ui.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class AddressPojo {

    private String company;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String homePhone;
    private String mobileNo;
    private String additionalInformation;
    private String addressTitle;
    private String state;
}
