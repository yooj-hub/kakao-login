package study.kakaologin.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import study.kakaologin.domain.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    @Builder
    public SessionUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public SessionUser(User entity) {
        this.name = entity.getName();
        this.email= entity.getEmail();
        this.picture = entity.getPicture();
    }
}
