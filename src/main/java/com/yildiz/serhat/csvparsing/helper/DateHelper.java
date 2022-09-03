package com.yildiz.serhat.csvparsing.helper;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateHelper {
    public static DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MM-yyyy")
            .toFormatter(Locale.ENGLISH);
}
