package com.example.farrukh.labs;

import android.util.Log;

/**
 * Created by Farrukh on 13.05.2017.
 */

public class A5_1Engine {

    private static String inputFile = null;
    private static String key = null;
    private static String frameNumber = null;
    private static final int BUFFERSIZE = 64;

    public static byte[] encrypt(String fileToEncrypt, String encryptKey, String encryptFrameNumber) {

        //Create new file handler and key stream handler for encryption
        KeyStreamGenerator keyStreamGenerator = new KeyStreamGenerator(encryptKey, encryptFrameNumber);

        long fSize = fileToEncrypt.length();
        Log.d("A5", "filesize=" + fSize);
        byte fileChunk;
        byte[] encryptedBytes = new byte[(int) fSize];
        byte keyStreamByte;

        keyStreamGenerator.init(); //initialization phase for the key stream

        for (long currByte = 0; currByte < fSize; currByte++) {
            //calculate the current frame
            try {
                fileChunk = (byte) fileToEncrypt.charAt((int) currByte);
                keyStreamByte = keyStreamGenerator.getStreamByte();
                // XOR with Plain text and key stream --> cipher text
                encryptedBytes[(int) (currByte % BUFFERSIZE)] = (byte) (keyStreamByte ^ fileChunk);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return encryptedBytes;
    }

    /**
     * @param fileToDecrypt
     * @param decryptKey
     * @param decryptFrameNumber
     * @return The filename of the decrypted file.
     */
    public static byte[] decrypt(String fileToDecrypt, String decryptKey, String decryptFrameNumber) {

        //Create new file handler and key stream handler for encryption
        KeyStreamGenerator keyStreamGenerator = new KeyStreamGenerator(decryptKey, decryptFrameNumber);

        long fSize = fileToDecrypt.length();
        byte fileChunk;
        byte[] decryptedBytes = new byte[(int) fSize];
        byte keyStreamByte;
        Log.d("A5", "filesize dec=" + fSize);

        keyStreamGenerator.init(); //initialization phase for the key stream

        for (long currByte = 0; currByte < fSize; currByte++) {
            //calculate the current frame
            try {
                fileChunk = (byte) fileToDecrypt.charAt((int) currByte);
                keyStreamByte = keyStreamGenerator.getStreamByte();
                // XOR with cipher text and key stream --> plain text
                decryptedBytes[(int) (currByte % BUFFERSIZE)] = (byte) (keyStreamByte ^ fileChunk);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Log.d("A5", "decrypted bytes = " + new String(decryptedBytes));
        return decryptedBytes;
    }

}
