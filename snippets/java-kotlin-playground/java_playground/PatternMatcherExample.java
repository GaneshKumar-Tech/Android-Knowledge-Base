package java_playground;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherExample {

    public static String specialChar = "";
    public static String numbers = "";
    public static String upperCase = "";
    public static String lowerCase = "";

    public static void main(String[] args) {

        String name = "$abcDeGh187%^";

        specialChar = patternMatcher("[^a-zA-Z0-9]", name);
        numbers = patternMatcher("\\d+", name);
        upperCase = patternMatcher("[A-Z]", name);
        lowerCase = patternMatcher("[a-z]", name);

        System.out.println("Special Characters: " + specialChar);
        System.out.println("Numbers: " + numbers);
        System.out.println("Uppercase: " + upperCase);
        System.out.println("Lowercase: " + lowerCase);
    }

    public static String patternMatcher(String regex, String text) {

        String result = "";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result += matcher.group();
        }

        return result;
    }
}


