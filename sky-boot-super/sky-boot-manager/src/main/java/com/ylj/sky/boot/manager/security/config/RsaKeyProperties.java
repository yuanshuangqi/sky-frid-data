package com.ylj.sky.boot.manager.security.config;

import com.ylj.sky.boot.common.util.RSAUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
@ConfigurationProperties("rsa.key")
@Data
public class RsaKeyProperties {
    private String pubKeyFile;
    private String priKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void createRsaKey() throws Exception {
        publicKey = RSAUtils.getPublicKey(pubKeyFile);
        privateKey = RSAUtils.getPrivateKey(priKeyFile);
    }

}
