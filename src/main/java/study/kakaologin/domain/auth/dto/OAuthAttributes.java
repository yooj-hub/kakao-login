package study.kakaologin.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import study.kakaologin.domain.Role;
import study.kakaologin.domain.User;

import java.util.Map;

@Getter
@Slf4j
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        return ofKakao("id", attributes);
    }

    private static OAuthAttributes ofKakao(String userNAmeAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAcount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAcount.get("profile");
        for (String s : kakaoAcount.keySet()) {
            log.info("{} = {}",s,kakaoAcount.get(s));
        }
        System.out.println("==");
        for (String s : kakaoProfile.keySet()) {
            log.info("{} = {}",s,kakaoProfile.get(s));
        }

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoProfile.get("email"))
                .picture((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNAmeAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();

    }


}
