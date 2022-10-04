package com.codestates.flyaway.domain.member.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.codestates.flyaway.domain.member.util.MemberUtil.*;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String raw) {
        return encode(raw);
    }

    @Override
    public String convertToEntityAttribute(String encoded) {
        return encoded;
    }
}
