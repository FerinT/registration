package com.registration.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.registration.utils.Validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @NotNull(message = Validator.USERNAME_IS_MANDATORY)
    @Pattern(regexp=Validator.USERNAME_PATTERN, message = Validator.USERNAME_SHOULD_BE_ALPHANUMERIC)
    private String username;

    @NotNull(message = Validator.PASSWORD_IS_MANDATORY)
    @Pattern(regexp=Validator.PASSWORD_PATTERN, message = Validator.PASSWORD_FORMAT_ERROR)
    private String password;

    @NotNull(message = Validator.DATE_OF_BIRTH_IS_MANDATORY)
    @Pattern(regexp = Validator.DATE_OF_BIRTH_PATTERN, message = Validator.DATE_OF_BIRTH_SHOULD_BE_IN_YYYY_MM_DD_FORMAT)
    private String dateOfBirth;

    @NotNull(message = Validator.SSN_IS_MANDATORY)
    @Pattern(regexp=Validator.SSN_PATTERN, message = Validator.SSN_FORMAT_IS_WRONG)
    private String socialSecurityNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getDateOfBirth(), user.getDateOfBirth()) &&
                Objects.equals(getSocialSecurityNumber(), user.getSocialSecurityNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getDateOfBirth(), getSocialSecurityNumber());
    }
}
