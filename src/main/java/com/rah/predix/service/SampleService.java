package com.rah.predix.service;

import com.rah.predix.builder.DataObjectBuilder;
import com.rah.predix.builder.DataObjectResponseBuilder;
import com.rah.predix.cache.LocalObjectCache;
import com.rah.predix.models.DataObject;
import com.rah.predix.models.DataObjectRequest;
import com.rah.predix.models.DataObjectResponse;
import com.rah.predix.util.SampleUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class SampleService {

    @Inject LocalObjectCache localDataObjects;

    public DataObjectResponse createDataObject (String authorizationToken, String tenantUuid, DataObjectRequest request) {
        DataObject object = DataObjectBuilder.createDataObject(request);
        localDataObjects.putObject(SampleUtils.getCacheKey(tenantUuid, request.getSourceKey()), object);
        return DataObjectResponseBuilder.createDataObjectResponse(object);
    }

    public DataObjectResponse getDataObject (String authorizationToken, String tenantUuid, String sourceKey) {
        DataObject object = localDataObjects.getObject(SampleUtils.getCacheKey(tenantUuid, sourceKey));
        if (object == null) {
            return null;
        }
        return DataObjectResponseBuilder.createDataObjectResponse(object);
    }

    public void deleteDataObject (String authorizationToken, String tenantUuid, String key) {
        localDataObjects.deleteObject(key);
    }

    public DataObjectResponse updateDataObject (String authorizationToken, String tenantUuid, String sourceKey, DataObjectRequest request) {
        DataObject object = DataObjectBuilder.createDataObject(request);
        localDataObjects.putObject(SampleUtils.getCacheKey(tenantUuid, sourceKey), object);
        return DataObjectResponseBuilder.createDataObjectResponse(object);
    }
}
