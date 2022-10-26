package com.codestates.flyaway.domain.member.util;

import com.codestates.flyaway.global.exception.BusinessLogicException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;

public class MemberUtil {

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
    public static void checkEmail(String email) {
        if (!Pattern.matches(REG_EMAIL, email)) {
            throw new BusinessLogicException(EMAIL_NOT_VALID);
        }
    }

    /**
     * Password 검증
     */
    public static void checkPassword(String password) {
        if (!Pattern.matches(REG_PASSWORD, password)) {
            throw new BusinessLogicException(PASSWORD_NOT_VALID);
        }
    }

    /**
     * Password 암호화
     * @return 암호화된 password
     */
    public static String encode(String password) {  // todo : salt 적용 (salt 컬럼 추가)

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
