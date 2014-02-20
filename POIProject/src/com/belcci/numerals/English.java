package com.belcci.numerals;
import java.math.BigDecimal;

/**
 * English Numerals
 * 
 * @author vit
 */
public class English extends AbstractNumeral {

    private static final String DECIMAL[] = {"zero", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine"};

    private static final String TENS[] = {"", "ten", "twenty", "thirty", "forty", "fifty",
        "sixty", "seventy", "eighty", "ninety"};

    private static final String TEENS[] = {"ten", "eleven", "twelve", "thirteen",
        "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    private static final String LIONS[] = {"", "thousand", "million",
        "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion",
        "octillion", "nonillion", "decillion"};

    /**
     * Number in word in english.
     * Sample:
     * 123 => one hundred twenty-three
     *
     * Supported only integer numbers: Integer, Long, Short, Byte, BigInteger
     * Max supported number: 1000000000000000000000000000000000000-1
     * 
     * @param number
     * @return
     */
    @Override
    public String format(Number number) {
        // check number type
        checkSupported(number);
        String text = number.toString();
        return formatImpl(text);
    }

    private String formatImpl(String text) {
        if ("0".equals(text)) {
            return DECIMAL[0];
        }
        StringBuilder sb = new StringBuilder();
        if (text.startsWith("-")) {
            sb.append("minus ");
            text = text.substring(1);
        }
        byte n[][] = Util.groups(text, 3);
        // [0,1,2],[3,5,6]
        for (int i = 0; i < n.length; ++i) {
            int k = n.length - i - 1;

            int h = n[i][0]; // сотни
            int t = n[i][1]; // десятки
            int u = n[i][2]; // единицы

            if (h > 0) {
                sb.append(DECIMAL[h]);
                sb.append(" ");
                sb.append("hundred");
                sb.append(" ");
            }
            //
            if (t == 0) {
                if (u > 0) {
                    sb.append(DECIMAL[u]);
                    sb.append(" ");
                }
            } else if (t == 1) {
                sb.append(TEENS[u]);
                sb.append(" ");
            } else {
                sb.append(TENS[t]);
                if (u > 0) {
                    sb.append("-");
                    sb.append(DECIMAL[u]);
                }
                sb.append(" ");
            }

            if (k > 0 && (h + t + u > 0)) {
                sb.append(LIONS[k]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }


    /**
     * 123.17 One hundred twenty-three and 17/100
     * 
     * @param bi
     * @return
     */
    @Override
     public String amount(BigDecimal bi) {
        String txt = bi.toPlainString();
        
        int point = txt.indexOf('.');
        StringBuilder sb = new StringBuilder();
        String rubli = txt;
        String kopeyki = "";
        if (point > 0) {
            rubli = txt.substring(0, point);
            kopeyki = txt.substring(point+1);
        }
        String celaya = formatImpl(rubli);
        sb.append(celaya);
        if (point > 0) {
            sb.append(" and ");
            sb.append(kopeyki);
            sb.append("/1");
            for (int i=0;i<kopeyki.length();++i) {
                sb.append("0");
            }
        }
        //
        Util.toUpperCaseFirstLetter(sb);
        return sb.toString();
     }
}
