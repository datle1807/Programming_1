
import java.util.Scanner;

public abstract class User {

    /* Declare variables */
    protected static Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;
    private String name;

    /* Constructor of class */
    public User(){}

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;

    }

    //Allowing viewing the requirements of the password and username
    public static void passwordConditions(){
        System.out.println("The password requirements are:\n" +
                "-8 to 12 characters\n" +
                "-It needs at least one uppercase\n" +
                "-At least one number\n" +
                "-At least one special character\n" +
                "-No space allowed");
    }
    public static void usernameConditions(){
        System.out.println("The username requirements are:\n" +
                "-8 to 30 characters\n" +
                "-The first character cannot be a special character\n" +
                "-It needs at least one uppercase\n" +
                "-The user can only decide to use either '-' or '_' as a symbol\n" +
                "-The user has the choice to add a number or more\n" +
                "-No space allowed");
    }
    //--------------------------------------------------------------------------------
    // Returns boolean statement for Password validation that is either True or False.
    //--------------------------------------------------------------------------------
    public static boolean validatePassword(String password) {
        //Checking the length of the password that should be between 8 and 12
        if (password.length() < 8 || password.length() >= 11) return false;

        boolean isUppercase = false;
        boolean isDigit = false;
        boolean isSymbol = false;

        for (char i: password.toCharArray()) {
            // Looping through each character of the String to see if it contains at least 1 uppercase and 1 alphabet
            if (i == Character.toUpperCase(i) && Character.isAlphabetic(i)) {
                isUppercase = true;
            }
            // Checking if it contains at least 1 digit
            if (Character.isDigit(i)) {
                isDigit = true;
            }
            // Checking if it contains at least a special character
            if (!Character.isAlphabetic(i) && !Character.isDigit(i) ) {
                isSymbol = true;
            }
        }
        // If it does not satisfy one of the condition it will return false else true.
        if (!isUppercase) return false;
        // no space is allowed
        if (password.contains(" ")) return false;

        if (!isDigit) return false;

        if (!isSymbol) return false;

        return true;
    }

    //--------------------------------------------------------------------------------
    // Returns boolean statement for Username validation that is either True or False.
    //--------------------------------------------------------------------------------

    public static boolean validateUsername(String username){
        // The length of the password should be between 8 and 30.
        if (username.length() < 8 || username.length() > 30) return false;

        boolean isUppercase = false;
        boolean isSymbol = false;

        //Similarly to the previous function, the first condition is the same
        for (char i: username.toCharArray()) {
            // Looping through each character of the String to see if it contains at least 1 uppercase and 1 alphabet
            if (i == Character.toUpperCase(i) && Character.isAlphabetic(i)) {
                isUppercase = true;
            }
            // The user can decide to add a special character or not that can only be '-' or '_'
            if (!Character.isAlphabetic(i) && !Character.isDigit(i) && i != '_' && i != '-') {
                isSymbol = true;
            }
        }
        //The username's first character cannot contain a special symbol or number.
        if (!Character.isAlphabetic(username.toCharArray()[0])) return false;

        if (!isUppercase) return false;
        //no space allowed
        if (username.contains(" ")) return false;

        if (isSymbol) return false;

        return true;

    }
    /* Getter and Setter of attributes */

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        if (validateUsername(username)) {
            this.username = username;
        }
        else {
            System.out.println("The username does not satisfy the requirements, please try again");
        }

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (validatePassword(password)) {
            this.password = password;
        }
        else {
            System.out.println("The password does not satisfy the requirements, please try again");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
