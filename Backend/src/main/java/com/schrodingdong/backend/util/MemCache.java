package com.schrodingdong.backend.util;

import com.schrodingdong.backend.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MemCache {
    public static final int MAX_SIZE = 3;
    private final Logger LOG = LoggerFactory.getLogger(MemCache.class);
    private Map<String, UserModel> cache = new HashMap<>();
    private List<String> cacheList = new LinkedList<>();


    public UserModel get(String uuid) {
        int index = cacheList.indexOf(uuid);
        if(index == -1) return null;
        cacheList.add(cacheList.remove(index));
        return cache.get(uuid);
    }

    public void put(String uuid, UserModel user) {
        // check the size of the cache
        if(cache.size() < MAX_SIZE){
            cache.put(uuid, user);
            cacheList.add(uuid);
        } else {
            // get rid of the Least Recently Used entry (aka the first one)
            String lruUuid = cacheList.get(0);
            cacheList.remove(0);
            cache.remove(lruUuid);
            // update !
            cacheList.add(user.getUuid());
            cache.put(user.getUuid(), user);
        }
    }

    @Override
    public String toString() {
        return "MemCache{" +"\n\t" +
                "cache=" + cache.keySet() + ",\n\t" +
                "cacheList=" + cacheList +"\n" +
                '}';
    }
}
