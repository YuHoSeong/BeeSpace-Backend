package com.creavispace.project.config.auth.dto;

import com.creavispace.project.domain.member.Role;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import com.creavispace.project.domain.member.entity.Member;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String loginType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
                           String loginType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
    }

    public static OAuthAttributes of(String registrationId, String memberNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("google".equals(registrationId)) {
            return ofGoogle(memberNameAttributeName, attributes, registrationId);
        }
        return ofNaver("id", attributes, registrationId);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes,
                                           String registrationId) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        System.out.println("------------------------네이버 로그인----------------------------");
        System.out.println("response = " + response);
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .loginType(registrationId)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String memberNameAttributeName, Map<String, Object> attributes,
                                            String registrationId) {
        System.out.println("------------------------구글 로그인----------------------------");
        System.out.println("attributes = " + attributes);
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .loginType(registrationId)
                .attributes(attributes)
                .nameAttributeKey(memberNameAttributeName)
                .build();
    }

    public Member toEntity() {
        String randomNickName = UUID.randomUUID().toString().substring(0, 8);
        MemberSaveRequestDto dto = MemberSaveRequestDto.builder()
                .memberEmail(email)
                .memberName(name)
                .loginType(loginType)
                .memberNickname(randomNickName)
                .role(Role.MEMBER).build();

        return new Member(dto);
    }
}
