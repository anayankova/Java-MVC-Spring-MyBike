package com.mybike.service.services.implementations;

import com.mybike.service.services.HashService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {
    @Override
    public String hash(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
