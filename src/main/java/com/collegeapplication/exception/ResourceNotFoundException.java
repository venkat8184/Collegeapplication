package com.collegeapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String resourceField;
    private long resourceValue;
    public ResourceNotFoundException(String resourceName,String resourceField,long resourceValue){
        super(String.format(" %s Not Found with %s: '%s'",resourceName,resourceField,resourceValue));
        this.resourceName=resourceName;
        this.resourceField=resourceField;
        this.resourceValue=resourceValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceField() {
        return resourceField;
    }

    public long getResourceValue() {
        return resourceValue;
    }
}
