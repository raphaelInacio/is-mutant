package com.mercadolibre.ismutant.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class Util {
    public static final Pattern dnaMatchPattern = Pattern.compile("(C{4}|A{4}|T{4}|G{4})");
    public static final Pattern noDnaMatch = Pattern.compile("(?!A|C|T|G)([A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])");
    public static final long baseNumberSequenceGene = 1;
    public static final String EMPTY_STRING = "";
    public static final BigDecimal PERCENT = new BigDecimal(100);
}
