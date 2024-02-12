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

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String memberNameAttributeName,
                                     Map<String, Object> attributes) {
        if ("google".equals(registrationId)) {

            return ofGoogle(memberNameAttributeName, attributes);
        }

        return ofNaver("id", attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        System.out.println("------------------------네이버 로그인----------------------------");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String memberNameAttributeName, Map<String, Object> attributes) {
        System.out.println("------------------------구글 로그인----------------------------");
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(memberNameAttributeName)
                .build();
    }

    public Member toEntity() {
        String randomNickName = UUID.randomUUID().toString().substring(0, 8);
        MemberSaveRequestDto dto = MemberSaveRequestDto.builder()
                .memberEmail(email)
                .memberName(name)
                .memberNickname(randomNickName)
                .role(Role.MEMBER).build();

        return new Member(dto);
    }
}
