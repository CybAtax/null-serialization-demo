package com.test.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class DemoWrapperWriteConverter implements Converter<DemoWrapper, String> {

    @Override
    public String convert(final DemoWrapper source) {
        return source.isUndefined() ? null : source.toString();
    }

}
