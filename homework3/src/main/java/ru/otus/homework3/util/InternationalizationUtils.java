package ru.otus.homework3.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class InternationalizationUtils {
    private final MessageSource messageSource;

    public InternationalizationUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, new Object[]{}, locale);
    }

    public String getMessage(String code, Locale locale, String... param) {
        return messageSource.getMessage(code, param, locale);
    }

    public String getLocalizedFileName(String fileName, Locale locale) {
        String[] fileNameParts = fileName.split("\\.");
        return fileNameParts[0] + "_" + locale.toString() + "." + fileNameParts[1];
    }
}
