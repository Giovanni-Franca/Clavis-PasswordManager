package Crypto;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {
	private static final String ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH = 128;

    // ⚠️ Em produção isso NÃO deve ficar hardcoded
    private static final byte[] KEY = "MinhaChaveSuperSegura123".getBytes(StandardCharsets.UTF_8);

    public static String encrypt(String texto) {
        try {
            byte[] iv = new byte[12];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);
            byte[] cipherText = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));

            byte[] encrypted = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(cipherText, 0, encrypted, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar", e);
        }
    }

    public static String decrypt(String textoCriptografado) {
        try {
            byte[] decoded = Base64.getDecoder().decode(textoCriptografado);

            byte[] iv = new byte[12];
            byte[] cipherText = new byte[decoded.length - 12];

            System.arraycopy(decoded, 0, iv, 0, 12);
            System.arraycopy(decoded, 12, cipherText, 0, cipherText.length);

            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH, iv);

            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);
            byte[] plainText = cipher.doFinal(cipherText);

            return new String(plainText, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar", e);
        }
    }

}
