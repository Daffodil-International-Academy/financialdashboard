package ac.daffodil.model;

import ac.daffodil.constraint.FieldMatch;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Uzzal on 7/3/2018.
 */
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String token;

    public PasswordResetDto() {
    }

    public PasswordResetDto(@NotEmpty String password, @NotEmpty String confirmPassword, @NotEmpty String token) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

