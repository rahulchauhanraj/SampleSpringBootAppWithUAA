package com.rah.predix.util;

public class SampleUtils {
    public static String getCacheKey(String tenantUuid, String key) {
        return tenantUuid + "SAMPLE_DATA_OBJECT_CACHE" + key;
    }
}
