import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author alexchen
 */
public class EncodeTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }
}
