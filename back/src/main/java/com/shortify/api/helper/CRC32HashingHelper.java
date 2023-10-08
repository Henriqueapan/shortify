package com.shortify.api.helper;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class CRC32HashingHelper {

    public static String getCRC32Representation(String originalString) {
        CRC32 crc32 = new CRC32();

        ByteBuffer byteBuffer = ByteBuffer.allocate(originalString.length());
        byteBuffer.put(originalString.getBytes());

        crc32.update(byteBuffer.array());

        return Long.toHexString(crc32.getValue());
    }
}
