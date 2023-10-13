public class Encryption {
    private static int a = 3;
    private static int b = 11;
    private static final long MOD = 65536;

    public static String encrypt(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            long encodedChar = ((long) a * text.charAt(i) + b) % MOD;
            stringBuilder.append((char) encodedChar);
        }
        text = stringBuilder.toString();
        return text;
    }

    public static String deсrypt(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            long tempElement = text.charAt(i);
            for (long x = 0; x <= MOD; x++) {
                if (((a * x) + b) % MOD == tempElement) {
                    tempElement = x;
                    break;
                }
            }
            stringBuilder.append((char) (tempElement));
        }
        text = stringBuilder.toString();
        return text;
    }


}
