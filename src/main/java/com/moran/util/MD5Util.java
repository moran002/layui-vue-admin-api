package com.moran.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.function.BiPredicate;

/**
 * @author moran
 */
public class MD5Util {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String[] CHARS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String generateSalt() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            buf.append(CHARS[RANDOM.nextInt(16)]);
        }
        return buf.toString();
    }

    public static String encrypt(CharSequence cs) {
        return encrypt(cs, "");
    }

    public static String encrypt(CharSequence cs, CharSequence salt) {
        String[] s = sort(new String[]{cs.toString(), salt.toString()}, (i1, i2) ->
                i1.compareTo(i2) > 0);
        String e = joinArrayWith("", s);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(e.getBytes(StandardCharsets.UTF_8));
            return fixedHexString(hashBytes);
        }
        catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static String fixedHexString(byte[] hashBytes) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < hashBytes.length; i++) {
            buf.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return buf.toString();
    }

    private static String joinArrayWith(String sep, String[] a) {
        if (null == a || a.length == 0) return "";
        StringBuilder buf = new StringBuilder();
        for (Object o : a)
            buf.append(o).append(sep);
        buf.setLength(buf.length() - sep.length());
        return buf.toString();
    }

    private static <T>T[] sort(T[] a, BiPredicate<T, T> p){
        if (null == a ||  a.length == 0) return a;
        T i1;
        int j;
        for(int i=1; i<a.length; i++){
            i1 = a[i];
            j = i - 1;
            for(;j >= 0 && p.test(a[j], i1); j--){
                a[j + 1] = a[j];
            }
            a[j + 1] = i1;
        }
        return a;
    }

    private static byte[] utf8Bytes(CharSequence cs) {
        return cs.toString().getBytes(StandardCharsets.UTF_8);
    }
}
