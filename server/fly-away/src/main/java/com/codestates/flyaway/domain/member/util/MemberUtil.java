package com.codestates.flyaway.domain.member.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class MemberUtil {  //todo : 다른 네이밍 방법 고려

    //이메일
    private static final String REG_EMAIL = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
    //    "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
    //비밀번호
    private static final String REG_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$";  //비번 최소 8글자, 글자, 숫자, 특수문자 1개
    private static final String ALGORITHM = "SHA-512";

    private MemberUtil() {
    }

    /**
     * Email 검증
     */
    public static void verifyEmail(String email) {
        if (!Pattern.matches(REG_EMAIL, email)) {
            throw new RuntimeException("이메일 형식이 올바르지 않습니다.");
        }
    }

    /**
     * Password 검증
     */
    public static void verifyPassword(String password) {
        if (!Pattern.matches(REG_PASSWORD, password)) {
            throw new RuntimeException("비밀번호 형식이 올바르지 않습니다.");
        }
    }

    /**
     * Password 암호화
     */
    public static String encode(String password) {  //시간되면 salt 적용 (salt 컬럼 추가)

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
