package ua.sych.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends idHolder {
    private String login;
    private String password;
    private String urlToPicture;
}
