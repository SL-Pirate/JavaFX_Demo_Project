package lk.ac.iit.genericfxproj.db;

import org.apache.commons.codec.digest.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Encryption {
    private String password;
    private String salt;
    private static String pepper;
    private String hash;

    public Encryption(String password){
        this.password = password;
        genSalt();
        getPepper();
        genHash();
    }
    public Encryption(String salt, String password) {
        this.password = password;
        this.salt = salt;
        getPepper();
        genHash();
    }

    private void genSalt(){
        byte[] array = new byte[10]; // length is bounded by 10
        new Random().nextBytes(array);
        salt = new String(array, StandardCharsets.UTF_8);
    }
    private static void getPepper () {
        pepper = System.getenv("pepper");
    }
    private void genHash(){
        hash = DigestUtils.sha512Hex(salt + password + pepper);
    }
    public String getHash(){
        return hash;
    }
    public String getSalt() {
        return salt;
    }

    public void updatePassword(String password) {
        this.password = password;

        genHash();
    }
}
