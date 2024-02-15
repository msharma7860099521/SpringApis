package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.List;

public class SecretKeyResolverAdapter implements SigningKeyResolver {

    private final SecretKey secretKey;

    public SecretKeyResolverAdapter(String base64EncodedSecret) {
        this.secretKey = Keys.hmacShaKeyFor(base64EncodedSecret.getBytes());
    }

    public Key resolveSigningKey(io.jsonwebtoken.Header header, Claims claims) {
        return secretKey;
    }

    public Key resolveSigningKey(io.jsonwebtoken.Header header, String plaintext) {
        return secretKey;
    }

    public Key resolveSigningKey(io.jsonwebtoken.Header header, byte[] bytes) {
        return secretKey;
    }

    public List<Key> resolveAllSigningKeys(io.jsonwebtoken.Header header, Claims claims) {
        return List.of(secretKey);
    }

    public List<Key> resolveAllSigningKeys(io.jsonwebtoken.Header header, String plaintext) {
        return List.of(secretKey);
    }

    public List<Key> resolveAllSigningKeys(io.jsonwebtoken.Header header, byte[] bytes) {
        return List.of(secretKey);
    }

	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key resolveSigningKey(JwsHeader header, String plaintext) {
		// TODO Auto-generated method stub
		return null;
	}
}
