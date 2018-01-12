package com.rah.predix.cache;

import com.rah.predix.models.DataObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LocalObjectCache {

    Map<String, DataObject> localMap = new HashMap<>();

    public DataObject getObject(String key) {
        return localMap.get(key);
    }

    public DataObject putObject(String key, DataObject value) {
        return localMap.put(key, value);
    }

    public DataObject deleteObject(String key) {
        return localMap.remove(key);
    }
}
