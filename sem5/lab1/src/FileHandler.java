import java.io.*;

public class FileHandler {
    private String text;
    private int a;
    private int b;

    private static final long MOD = 65536;

    public String readFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        while((str = bufferedReader.readLine()) != null){
            stringBuilder.append(str);
        }
        text = stringBuilder.toString();
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public void writeToFile(File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(text);
        bufferedWriter.close();
    }

    public String encode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            long encodedChar = ((long) a * text.charAt(i) + b) % MOD;
            stringBuilder.append((char) encodedChar);
        }
        text = stringBuilder.toString();
        return text;
    }

    public String decode() {
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

    public String getText() {
        return text;
    }

    public void setParams(int a, int b){
        this.a = a;
        this.b = b;
    }
}
