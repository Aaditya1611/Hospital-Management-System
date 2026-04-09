package com.example.auth.api.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.example.auth.api.signup.type.AuthProviderType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthUtil {
    
    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId) {

        return switch(registrationId.toLowerCase()) {
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            case "twitter" -> AuthProviderType.TWITTER;
            case "linkedin" -> AuthProviderType.LINKEDIN;

            default ->  throw new IllegalArgumentException("Unsupported OAuth2 provider" + registrationId);
        };
    }
 
    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User, String registrationId) {
        
        String providerId = switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();

            default -> {
                log.error("unsupported oAuth2 provider: {}", registrationId);
                throw new IllegalArgumentException("Unsupported Oauth2 provider");
            }};
            if (providerId == null || providerId.isBlank()) {
                log.error("unable to determine providerId for provider: {}", registrationId);
                throw new IllegalArgumentException("Unable to determine providerId for Oauth2 login");
            };
            return providerId;
    }

    public String determineEmailFromOAuth2User(OAuth2User oAuth2User, String registrationId, String providerId) {

        String email = oAuth2User.getAttribute("email");
        if(email != null && !email.isBlank()) {
            return email;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }
}
