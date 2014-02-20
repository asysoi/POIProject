package com.belcci.numerals;
import java.math.BigDecimal;

/**
 * 
 * <pre>
 * import java.math.BigDecimal;
 * import java.math.BigInteger;
 * import static com.programmisty.numerals.Numerals.*;
 * 
 * public class Sample {
 * 
 *         public static void main(String[] args) {
 *                 // one hundred twenty-three
 *                 System.out.println(english(123));
 * 
 *                 // one hundred twenty-three thousand four hundred fifty-six
 *                 System.out.println(english(new BigInteger(&quot;123456&quot;)));
 * 
 *                 // Ninety-nine and 89/100
 *                 System.out.println(amount(new BigDecimal(&quot;99.89&quot;)));
 *         }
 * }
 * </pre>
 * 
 * @author vit
 */
public class Numerals {

        /**
         * Number in word in english. Sample:
         * 
         * <pre>
         * Numerals.english(123); // one hundred twenty-three
         * </pre>
         * 
         * Supported only integer numbers: Integer, Long, Short, Byte, BigInteger
         * Max supported number: 1000000000000000000000000000000000000-1
         * 
         * @param number
         * @return
         */
        public static String english(Number n) {
                return new English().format(n);
        }

        /**
         * Number in word in russian. Число прописью по-русски. Sample:
         * 
         * <pre>
         * Numerals.russian(123); // семьсот семьдесят семь
         * </pre>
         * 
         * Supported only integer numbers: Integer, Long, Short, Byte, BigInteger
         * Max supported number: 1000000000000000000000000000000000000-1
         * 
         * @param number
         * @return
         */
        public static String russian(Number n) {
                return new Russian().format(n);
        }


        /**
         * Amount in words. English. Sometimes useful in payment docs.
         * 
         * <pre>
         * BigDecimal x = new BigDecimal(&quot;99.89&quot;);
         * Numerals.amount(x); // Ninety-nine and 89/100
         * </pre>
         * 
         * @param n
         * @return
         */
        public static String amount(Number n) {
                BigDecimal bd = Util.toBigDecimal(n);
                return new English().amount(bd);
        }

        /**
         * Amount in words. Russian. Sometimes useful in finan docs. Сумма прописью.
         * Бывает используются в разных платежных документах.
         * 
         * <pre>
         * BigDecimal x = new BigDecimal(&quot;777.77&quot;);
         * Numerals.russianRubles(x); // Семьсот семьдесят семь рублей 77 копеек
         * </pre>
         * 
         * @param n
         * @return
         */
        public static String russianRubles(Number n) {
                BigDecimal bd = Util.toBigDecimal(n);
                return new Russian().amount(bd);
        }

}