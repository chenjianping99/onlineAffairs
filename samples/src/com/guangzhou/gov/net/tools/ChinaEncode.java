package com.guangzhou.gov.net.tools;

import java.net.URLEncoder;

/**
 * encode zh 2 utf-8
 * 
 * @ClassName: ChinaEncode
 * @author chenjianping
 * @date 2014-11-9
 * 
 */
public class ChinaEncode {
    public static boolean isAscii(char ch)
    {
        return ch <= 126;
    }

    public static String encodeURI(String url)
    {
        if (url == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < url.length(); i++) {
            char ch = url.charAt(i);
            if (isAscii(ch)) {
                switch (ch)
                {
                    case '"':
                        sb.append("%22");
                        break;
                    case '%':
                        sb.append("%25");
                        break;
                    case '<':
                        sb.append("%3C");
                        break;
                    case '>':
                        sb.append("%3E");
                        break;
                    case '[':
                        sb.append("%5B");
                        break;
                    case ']':
                        sb.append("%5D");
                        break;
                    case '^':
                        sb.append("%5E");
                        break;
                    case '`':
                        sb.append("%60");
                        break;
                    case '{':
                        sb.append("%7B");
                        break;
                    case '|':
                        sb.append("%7C");
                        break;
                    case '}':
                        sb.append("%7D");
                        break;
                    case ' ':
                        sb.append("%20");
                        break;
                    default:
                        sb.append(ch);
                        break;
                }
            } else {
                try {
                    sb.append(URLEncoder.encode(Character.toString(ch), "UTF-8"));
                } catch (Exception e) {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

}
