package helpers;

public class EmailGenerator {

    public enum EmailType {
        VALID,
        WITHOUT_AT,
        WITHOUT_USERNAME,
        WITHOUT_DOMAIN,
        WITHOUT_DOT,
        WITH_INVALID_CHARS
    }

    public static String generateEmail(EmailType type, int a, int b, int c) {
        switch (type) {
            case VALID:
                return generateValidEmail(a, b, c);
            case WITHOUT_AT:
                return generateEmailWithoutAt(a, b, c);
            case WITHOUT_USERNAME:
                return generateEmailWithoutUsername(b, c);
            case WITHOUT_DOMAIN:
                return generateEmailWithoutDomain(a);
            case WITHOUT_DOT:
                return generateEmailWithoutDot(a, b);
            case WITH_INVALID_CHARS:
                return generateEmailWithInvalidChars(a, b, c);
            default:
                throw new IllegalArgumentException("Unknown email type");
        }
    }

    private static String generateValidEmail(int a, int b, int c) {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append("@");
        for (int i = 0; i < b; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append(".");
        for (int i = 0; i < c; i++) {
            email.append(randomCharUsingNumbers());
        }
        return email.toString();
    }

    private static String generateEmailWithoutAt(int a, int b, int c) {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++) {
            email.append(randomCharUsingNumbers());
        }
        // Пропускаем "@"
        for (int i = 0; i < b; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append(".");
        for (int i = 0; i < c; i++) {
            email.append(randomCharUsingNumbers());
        }
        return email.toString();
    }

    private static String generateEmailWithoutUsername(int b, int c) {
        StringBuilder email = new StringBuilder();
        email.append("@");
        for (int i = 0; i < b; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append(".");
        for (int i = 0; i < c; i++) {
            email.append(randomCharUsingNumbers());
        }
        return email.toString();
    }

    private static String generateEmailWithoutDomain(int a) {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append("@");
        email.append(".");
        return email.toString();
    }

    private static String generateEmailWithoutDot(int a, int b) {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++) {
            email.append(randomCharUsingNumbers());
        }
        email.append("@");
        for (int i = 0; i < b; i++) {
            email.append(randomCharUsingNumbers());
        }
        return email.toString();
    }

    private static String generateEmailWithInvalidChars(int a, int b, int c) {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < a; i++) {
            email.append(randomInvalidChar());
        }
        email.append("@");
        for (int i = 0; i < b; i++) {
            email.append(randomInvalidChar());
        }
        email.append(".");
        for (int i = 0; i < c; i++) {
            email.append(randomInvalidChar());
        }
        return email.toString();
    }

    private static char randomCharUsingNumbers() {
        int rand = (int) (Math.random() * 3);

        switch (rand) {
            case 0:
                return (char) ('0' + Math.random() * 10);
            case 1:
                return (char) ('A' + Math.random() * 26);
            case 2:
            default:
                return (char) ('a' + Math.random() * 26);
        }
    }

    private static char randomInvalidChar() {
        String invalidChars = " !\"#$%&'()*+,/:;<=>?@[\\]^_`{|}~";
        return invalidChars.charAt((int) (Math.random() * invalidChars.length()));
    }
}
