package com.isilona.hrm.exception;

import java.util.UUID;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(UUID resourceUuid) {
        super(String.format("Resource with id %s was not found", resourceUuid));
    }

    public ResourceNotFoundException(Class resourceType, UUID resourceUuid) {
        super(String.format("Resource (%s) with id %s was not found", resourceType.getCanonicalName(), resourceUuid));
    }

    public ResourceNotFoundException(Class resourceType, String propertyName, String propertyValue) {
        super(String.format("Resource (%s) with %s = %s was not found", resourceType.getCanonicalName(), propertyName, propertyValue));
    }

}

