package com.belcci.numerals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


/**
 * Russian numbers
 * 
 * @author vit
 */
public class Russian extends AbstractNumeral {

    private static final String EDINICHI[] = {"����", "����", "���", "���", "������",
        "����", "�����", "����", "������", "������"};
    private static final String DESYAT[] = {"������", "�����������", "����������", "����������", "������������",
        "����������", "�����������", "����������", "�������������", "������������"};
    private static final String DESYATKI[] = {"", "������", "��������", "��������", "�����", "���������", "����������",
        "���������", "�����������", "���������"};
    private static final String SOTNI[] = {"", "���", "������", "������", "���������", "�������", "��������",
        "�������", "���������", "���������"};
    private static final String LIONS[] = {"", "������", "�������",
        "��������", "��������", "�����������", "�����������", "�����������", "����������",
        "���������", "���������", "���������"
    };

    @Override
    public String format(Number number) {
        // check number type
        checkSupported(number);
        // 
        return formatImpl(number.toString());
    }

    private String formatImpl(String text) {
        if ("0".equals(text)) {
            return EDINICHI[0];
        }
        StringBuilder sb = new StringBuilder();
        if (text.startsWith("-")) {
            sb.append("����� ");
            text = text.substring(1);
        }

        byte n[][] = Util.groups(text, 3);


        for (int i = 0; i < n.length; ++i) {
            // 1 = 1000, 2 = 1 000 000
            int k = n.length - i - 1;

            int h = n[i][0]; // �����
            int t = n[i][1]; // �������
            int u = n[i][2]; // �������
            if (h == 0 && t == 0 && u == 0) {
                // ���� ����� 
                continue;
            }
            // ���� ��������...
            if (h > 0) {
                String sotni = SOTNI[h];
                sb.append(sotni);
                sb.append(" ");
            }
            // ������� ���
            if (t == 0) {
                // 
                if (u > 0) {
                    String txt = EDINICHI[u];
                    if (k == 1) {
                        switch (u) {
                            case 1:
                                txt = "����";
                                break;
                            case 2:
                                txt = "���";
                                break;
                        }
                    }
                    sb.append(txt);
                    sb.append(" ");
                }
                // 
            } else if (t == 1) {
                sb.append(DESYAT[u]);
                sb.append(" ");
            } else if (t > 1) {
                // 21 - �������� ���� � ������
                sb.append(DESYATKI[t]);
                if (u > 0) {
                    sb.append(" ");
                    String ed =  EDINICHI[u];
                    if (k == 1) {
                        switch (u) {
                            case 1:
                                ed = "����";
                                break;
                            case 2:
                                ed = "���";
                                break;
                            default:
                        }
                    }
                    sb.append(ed);
                }
                sb.append(" ");
            }
            // ���� ��� ��� ������ ���� ����� ���� ������ ������ ������
            if (k > 0 && (h + t + u > 0)) {
                if (k == 1) {
                    sb.append(tisyachi(h, t, u));
                } else {
                    sb.append(lions(h, t, u, k));
                }
                // 
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private static char lastNonWhitespace(CharSequence sb) {
        for (int i = sb.length() - 1; i >= 0; i--) {
            char ch = sb.charAt(i);
            if (!Character.isWhitespace(ch)) {
                return ch;
            }
        }
        return 0;
    }

    static String lions(int h, int t, int u, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(LIONS[k]);

        if (t == 0 || t > 1) {
            switch (u) {
                case 1:
                    break;

                case 2:
                case 3:
                case 4:
                    sb.append("�");
                    break;

                default:
                    sb.append("��");
                    break;
            }
        } else {
            sb.append("��");
        }
        return sb.toString();
    }

    static String tisyachi(int h, int t, int u) {
        String result = "�����";
        // �� 0 �� 9 ��� h*100
        if (t == 0 || t > 1) {
            switch (u) {
                case 1:
                    result = "������";
                    break;
                case 2:
                case 3:
                case 4:
                    result = "������";
                    break;
            }
        }
        return result;
    }

    /**
     * ����� ��������: ������� ��������� ���� ������ 77 ������
     * @param bi
     * @return
     */
    @Override
    public String amount(BigDecimal bi) {
        String txt = bi.toPlainString();

        int point = txt.indexOf('.');
        StringBuilder sb = new StringBuilder();
        String rubli = txt;
        if (point > 0) {
            rubli = txt.substring(0, point);
        }
        String celaya = formatImpl(rubli);
        sb.append(celaya);
        sb.append(" ");
        String currency = rubley(bi);

        sb.append(currency);
        sb.append(" ");
        int k = roundKopeyki(bi);
        assert (k >= 0 && k < 100);

        if (k < 10) {
            sb.append("0");
            sb.append(k);
        } else {
            sb.append(k);
        }
        sb.append(" ");
        sb.append(kopeyki(k));
        // to upper case
        Util.toUpperCaseFirstLetter(sb);
        return sb.toString();
    }

    private static int roundKopeyki(BigDecimal b) {
        b = b.abs();
        int k = b.multiply(BigDecimal.valueOf(100)).remainder(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP).intValue();
        return k;
    }

    private static String kopeyki(int k) {
        String result = "������";
        if (k > 10 && k < 20) {
            result = "������";
        } else {
            int last = k % 10;
            switch (last) {
                case 1:
                    result = "�������";
                    break;
                case 2:
                case 3:
                case 4:
                    result = "�������";
                    break;
                default:
                    result = "������";
            }
        }
        return result;
    }

    private static String rubley(BigDecimal amount) {
        BigInteger r = amount.setScale(0, RoundingMode.DOWN).toBigInteger();
        String result = "������";
        r = r.remainder(BigInteger.valueOf(100));
        if (r.compareTo(BigInteger.TEN) > 0 && r.compareTo(BigInteger.valueOf(20)) < 0) {
            result = "������";
        } else {
            int last = r.remainder(BigInteger.TEN).intValue();
            switch (last) {
                case 1:
                    result = "�����";
                    break;
                case 2:
                case 3:
                case 4:
                    result = "�����";
                    break;
                default:
                    result = "������";
            }
        }
        return result;
    }

}
