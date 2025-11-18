public class KeywordCaesar {

    // Chuyển ký tự key thành shift: A=0, B=1, ..., Z=25
    private int charToShift(char c) {
        return Character.toUpperCase(c) - 'A';
    }

    // Mã hóa theo Keyword Caesar
    public String encrypt(String text, String key) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            int shift = charToShift(key.charAt(i % key.length()));
            char c = (char) ((text.charAt(i) - 'A' + shift) % 26 + 'A');
            ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    // Giải mã Keyword Caesar
    public String decrypt(String cipher, String key) {
        cipher = cipher.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {
            int shift = charToShift(key.charAt(i % key.length()));
            char c = (char) ((cipher.charAt(i) - 'A' - shift + 26) % 26 + 'A');
            plaintext.append(c);
        }
        return plaintext.toString();
    }

    // Test nhanh
    public static void main(String[] args) {
        KeywordCaesar kc = new KeywordCaesar();
        String plain = "HANHDONGXY";
        String key = "BIMAT";

        String cipher = kc.encrypt(plain, key);
        System.out.println("Ciphertext: " + cipher);

        String decrypted = kc.decrypt(cipher, key);
        System.out.println("Decrypted : " + decrypted);
    }
}
