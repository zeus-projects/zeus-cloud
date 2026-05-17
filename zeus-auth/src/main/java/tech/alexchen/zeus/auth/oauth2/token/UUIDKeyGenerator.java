package tech.alexchen.zeus.auth.oauth2.token;

import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.UUID;

public class UUIDKeyGenerator implements StringKeyGenerator {

    @Override
    public String generateKey() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
