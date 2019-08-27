package com.registration.utils;

public class Validator {

    public static final String USER_ALREADY_REGISTERED = "This user already exists";
    public static final String USER_EXCLUDED = "This user has been excluded";
    public static final String SUCCESSFULLY_REGISTERED = "User has been successfully registered";

    public static final String SSN_FORMAT_IS_WRONG = "Invalid SSN format";
    public static final String SSN_IS_MANDATORY = "SSN is mandatory";

    public static final String SSN_PATTERN = "^[0-9]{8,9}$";
    public static final String DATE_OF_BIRTH_PATTERN = "^(18|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$";
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(.){4,40}$";
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{1,40}$";

    public static final String USERNAME_SHOULD_BE_ALPHANUMERIC = "Username should be alphanumeric";
    public static final String USERNAME_IS_MANDATORY = "Username is mandatory";

    public static final String PASSWORD_FORMAT_ERROR = "Password must include at least four characters including one upper case character and at least one number";
    public static final String PASSWORD_IS_MANDATORY = "Password is mandatory";

    public static final String DATE_OF_BIRTH_SHOULD_BE_IN_YYYY_MM_DD_FORMAT = "Date of birth should be in yyyy-MM-dd format";
    public static final String DATE_OF_BIRTH_IS_MANDATORY = "Date of birth is mandatory";
}
