package com.xqh.serverfile.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * FileTypeUtil
 *
 * @author yz3702
 * @date 2019/11/15 17:00
 **/
public class FileTypeUtil {
    public static final Map<String, String> FILE_TYPE_MAP = new HashMap<>();
    static {
        // images
        FILE_TYPE_MAP.put("FFD8FF", ".jpg");
        FILE_TYPE_MAP.put("89504E47", ".png");
        FILE_TYPE_MAP.put("424D", ".bmp");
    }
}
