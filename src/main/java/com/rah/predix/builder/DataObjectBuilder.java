package com.rah.predix.builder;

import com.rah.predix.models.DataObject;
import com.rah.predix.models.DataObjectRequest;

public class DataObjectBuilder {

    public static DataObject createDataObject (DataObjectRequest request) {
        return new DataObject(request.getId(), request.getName(), request.getValue());
    }
}
