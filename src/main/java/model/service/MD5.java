package model.service;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Provalov on 23.09.2017.
 */
public class MD5 {
    public static String md5Hex(String pass) {
        return DigestUtils.md5Hex(pass);
    }
}
