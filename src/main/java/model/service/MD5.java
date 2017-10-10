package model.service;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

/**
 * Created by Provalov on 23.09.2017.
 */
public class MD5 {
    public static String md5Hex(String pass) {
        Optional<String> result = Optional.ofNullable(pass);
        return DigestUtils.md5Hex(result.get());
    }
}
