
public class TranspositionCipher {

    // Mã hóa hoán vị theo block (row-wise block-wise)
    public String encryptRowWise(String text, int[] key) {
        char[] chars = text.toCharArray();
        char[] encrypted = new char[chars.length];
        int blockSize = key.length;

        // Duyệt từng block
        for (int start = 0; start < chars.length; start += blockSize) {
            for (int i = 0; i < blockSize && (start + i) < chars.length; i++) {
                encrypted[start + i] = chars[start + key[i] - 1];
            }
        }
        return new String(encrypted);
    }

    // Giải mã hoán vị theo block
    public String decryptRowWise(String text, int[] key) {
        char[] chars = text.toCharArray();
        char[] decrypted = new char[chars.length];
        int blockSize = key.length;

        // Duyệt từng block
        for (int start = 0; start < chars.length; start += blockSize) {
            for (int i = 0; i < blockSize && (start + i) < chars.length; i++) {
                decrypted[start + key[i] - 1] = chars[start + i];
            }
        }
        return new String(decrypted);
    }

    // Test nhanh
    public static void main(String[] args) {
        TranspositionCipher tc = new TranspositionCipher();
        int[] key = {3,5,1,4,2};
        String plaintext = "hanhdongxy";

        String ciphertext = tc.encryptRowWise(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext); // ndhhagyoxn

        String decrypted = tc.decryptRowWise(ciphertext, key);
        System.out.println("Decrypted: " + decrypted); // hanhdongxy
    }
}
