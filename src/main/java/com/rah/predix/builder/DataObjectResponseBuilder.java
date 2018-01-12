package com.rah.predix.builder;

import com.rah.predix.models.DataObject;
import com.rah.predix.models.DataObjectResponse;

public class DataObjectResponseBuilder {
    public static DataObjectResponse createDataObjectResponse (DataObject object) {
        return new DataObjectResponse(object.getId(), object.getName(), object.getValue());
    }
}
