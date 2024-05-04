package com.riwi.beauty_center.api.error_handler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseErrorResponse implements Serializable{
    
    private String status;
    private Integer code;
}
