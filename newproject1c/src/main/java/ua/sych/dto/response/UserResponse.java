package ua.sych.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.sych.entity.User;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String login;
    private String password;
    private String urlToPicture;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.urlToPicture=user.getUrlToPicture();
    }
}
