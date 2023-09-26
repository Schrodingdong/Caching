package com.schrodingdong.backend.service;

import com.schrodingdong.backend.model.UserModel;
import com.schrodingdong.backend.repository.UserRepository;
import com.schrodingdong.backend.util.MemCache;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private Map<String, UserModel> cache = new HashMap<>();
    private MemCache memCache;

    @PostConstruct
    private void init(){
        this.memCache = new MemCache();
    }

    public UserModel getUser(String uuid) {
        return userRepository.findById(uuid).orElse(null);
    }
    public UserModel getUserMemoryCache(String uuid) {
        // check cache
        UserModel cacheResult = memCache.get(uuid);
        if(cacheResult != null) {
            LOG.info("Cache hit");
            return cacheResult;
        }
        // if not in cache, get from db and put in cache
        LOG.info("Cache miss");
        UserModel user = userRepository.findById(uuid).orElse(null);
        if(user == null) return null;
        memCache.put(user.getUuid(), user);
        LOG.info("Cache state: " + memCache.toString());
        return user;
    }
}
