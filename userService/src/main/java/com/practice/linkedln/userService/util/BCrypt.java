package com.practice.linkedln.userService.util;

public class BCrypt {

    public static String hash(String s){
        return org.mindrot.jbcrypt.BCrypt.hashpw(s, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    public static boolean match(String passwordText, String passwordHashed){
        return org.mindrot.jbcrypt.BCrypt.checkpw(passwordText, passwordHashed);
    }
}
