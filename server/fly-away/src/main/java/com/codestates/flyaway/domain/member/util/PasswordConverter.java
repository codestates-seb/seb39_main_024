package com.codestates.flyaway.domain.member.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "SHA-512";

    @Override
    public String convertToDatabaseColumn(String raw) {
        return encode(raw);
    }

    @Override
    public String convertToEntityAttribute(String encoded) {
        return encoded;
    }

    /**
     * Password 암호화
     * @return 암호화된 비밀번호
     */
    public static String encode(String password) {  //시간되면 salt 적용

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
