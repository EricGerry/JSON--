package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Result {

    private boolean success;
    private String code;
    private String message;
    private Object data;
}
