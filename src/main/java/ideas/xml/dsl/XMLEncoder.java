package ideas.xml.dsl;

public class XMLEncoder {
    static public String encodeAllExceptApostrophe(String aString) throws NullPointerException {
        if (aString == null) {
            throw new NullPointerException("aString");
        }
        int vStringLength = aString.length();
        StringBuilder vResult = new StringBuilder(vStringLength);
        int vLoop = 0;
        while (vLoop < vStringLength) {
            char vChar = aString.charAt(vLoop);

            switch (vChar) {
                case '&':
                    vResult.append("&amp;");
                    break;
                case '<':
                    vResult.append("&lt;");
                    break;
                case '>':
                    vResult.append("&gt;");
                    break;
                case '"':
                    vResult.append("&quot;");
                    break;
                default:
                    if (vChar < 32) {
                        vResult.append(' ');
                    } else {
                        vResult.append(vChar);
                    }
            }
            vLoop += 1;
        }

        return vResult.toString();
    }

    static public String encode(String aString) {
        if (aString == null) {
            throw new java.lang.NullPointerException("Empty encoded field.");
        }

        int vStringLength = aString.length();
        StringBuilder vResult = new StringBuilder(vStringLength);
        int vLoop = 0;
        while (vLoop < vStringLength) {
            char vChar = aString.charAt(vLoop);

            switch (vChar) {
                case '&':
                    vResult.append("&amp;");
                    break;
                case '<':
                    vResult.append("&lt;");
                    break;
                case '>':
                    vResult.append("&gt;");
                    break;
                case '"':
                    vResult.append("&quot;");
                    break;
                case '\'':
                    vResult.append("&apos;");
                    break;
                default:
                    if (vChar < 32) {
                        vResult.append(' ');
                    } else if (vChar > 127) {
                        vResult.append('&')
                                .append('#')
                                .append((int) vChar)
                                .append(';');
                    } else {
                        vResult.append(vChar);
                    }
            }
            vLoop += 1;
        }
        return vResult.toString();
    }
}
