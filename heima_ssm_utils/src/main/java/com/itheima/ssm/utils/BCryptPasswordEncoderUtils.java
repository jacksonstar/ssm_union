package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {


    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password){
        String zhangsan = bCryptPasswordEncoder.encode(password);
        return zhangsan;
    }

    public static void main(String[] args) {
        String encode = encode("123");
        System.out.println(encode);
        //$2a$10$8o2paq2dA8ZsdM9SnosbseBRvPETqrlvfpworvZFAFQpzVpYnWQy6
        //$2a$10$TUFXmUqKGtMSBHN/wWOYne3ism.Evi4eAgep/MFpt9plzcDxcMnZW
        //$2a$10$XVhzd3AvMrJbMC/u0nf2cOujBopk2XKCJW5.1zm2xepDtkMHSi9fy

    }
}
