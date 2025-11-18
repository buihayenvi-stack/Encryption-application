
public class PlayfairCipher {

    private final char[][] matrix = new char[5][5];
    private final String key;

    public PlayfairCipher(String key) {
        this.key = prepareKey(key);
        createMatrix();
    }

    // Chuẩn hóa key: loại bỏ ký tự trùng lặp, thay J bằng I
    private String prepareKey(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder sb = new StringBuilder();
        for (char c : key.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Tạo ma trận 5x5
    private void createMatrix() {
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // J bị bỏ
        String fullKey = key;
        for (char c : alphabet.toCharArray()) {
            if (fullKey.indexOf(c) == -1) {
                fullKey += c;
            }
        }

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = fullKey.charAt(k++);
            }
        }
    }

    // Mã hóa plaintext
    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder sb = new StringBuilder();

        // Chia plaintext thành các cặp ký tự
        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';
            if (a == b) b = 'X';

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            // Quy tắc Playfair
            if (posA[0] == posB[0]) { // cùng hàng
                sb.append(matrix[posA[0]][(posA[1]+1)%5]);
                sb.append(matrix[posB[0]][(posB[1]+1)%5]);
            } else if (posA[1] == posB[1]) { // cùng cột
                sb.append(matrix[(posA[0]+1)%5][posA[1]]);
                sb.append(matrix[(posB[0]+1)%5][posB[1]]);
            } else { // hình chữ nhật
                sb.append(matrix[posA[0]][posB[1]]);
                sb.append(matrix[posB[0]][posA[1]]);
            }
        }

        return sb.toString();
    }

    // Giải mã ciphertext
    public String decrypt(String ciphertext) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // cùng hàng
                sb.append(matrix[posA[0]][(posA[1]+4)%5]);
                sb.append(matrix[posB[0]][(posB[1]+4)%5]);
            } else if (posA[1] == posB[1]) { // cùng cột
                sb.append(matrix[(posA[0]+4)%5][posA[1]]);
                sb.append(matrix[(posB[0]+4)%5][posB[1]]);
            } else { // hình chữ nhật
                sb.append(matrix[posA[0]][posB[1]]);
                sb.append(matrix[posB[0]][posA[1]]);
            }
        }

        return sb.toString();
    }

    // Tìm vị trí ký tự trong ma trận
    private int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Không tìm thấy (không xảy ra nếu dữ liệu chuẩn)
    }

    // In ma trận (dễ debug)
    public void printMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Test nhanh
    public static void main(String[] args) {
        PlayfairCipher cipher = new PlayfairCipher("MONARCHY");
        String plaintext = "HELLO";
        String ciphertext = cipher.encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted: " + cipher.decrypt(ciphertext));
    }
}
