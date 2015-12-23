package ideas.xml.dsl;

public class XMLEncoder {
    static public String encodeAllExceptApostrophe(String string) throws NullPointerException {
        if (string == null) {
            throw new NullPointerException("Empty encode field");
        }
        int stringLength = string.length();
        StringBuilder result = new StringBuilder(stringLength);
        for (int loop = 0; loop < stringLength; loop += 1) {
            char ch = string.charAt(loop);

            switch (ch) {
                case '&':
                    result.append("&amp;");
                    break;
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    if (ch < 32) {
                        result.append(' ');
                    } else {
                        result.append(ch);
                    }
            }
        }

        return result.toString();
    }

    static public String encode(String string) {
        if (string == null) {
            throw new NullPointerException("Empty encoded field");
        }

        int stringLength = string.length();
        StringBuilder result = new StringBuilder(stringLength);
        for (int loop = 0;loop < stringLength; loop += 1) {
            char ch = string.charAt(loop);

            switch (ch) {
                case '&':
                    result.append("&amp;");
                    break;
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&apos;");
                    break;
                default:
                    if (ch < 32) {
                        result.append(' ');
                    } else if (ch > 127) {
                        result.append('&')
                                .append('#')
                                .append((int) ch)
                                .append(';');
                    } else {
                        result.append(ch);
                    }
            }
        }
        return result.toString();
    }
}
