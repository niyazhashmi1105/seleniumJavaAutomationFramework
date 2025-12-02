package com.ui.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Config {

    Map<String,Environment> environments;

}
