package utilitiesLayer;

import java.util.regex.*;

/**
 * Email validator class to validate email addresses
 * 
 * {@link} https://mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
 */
public class EmailValidator {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$"; // Regex pattern for email addresses

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

}
