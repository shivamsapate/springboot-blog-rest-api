package com.shivam.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public String resourceName;
    public String fieldName;
    public String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue)); // post(resource) not found with id : value;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
