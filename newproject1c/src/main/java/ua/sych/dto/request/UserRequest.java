package ua.sych.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Field 'login' can not be null")
    @Size(min = 2, max = 10)
    private String login;
    @NotNull(message = "Field 'password' can not be null")
    @Size(min = 2, max = 10)
    private String password;
}
