package com.example.auth.api.oauth;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.auth.api.dto.LoginResponseDTO;
import com.example.auth.api.dto.SignupRequestDTO;
import com.example.auth.api.jwt.JwtService;
import com.example.auth.api.signup.Patient;
import com.example.auth.api.signup.PatientRepo;
import com.example.auth.api.signup.User;
import com.example.auth.api.signup.UserRepo;
import com.example.auth.api.signup.type.AuthProviderType;
import com.example.auth.api.signup.type.RoleType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PatientRepo patientRepo;

    public User signupInternal(SignupRequestDTO signupRequestDTO, AuthProviderType authProviderType,
            String providerId) {

        User user = userRepo.findByUsername(signupRequestDTO.getUsername());
        if (user != null)
            throw new IllegalArgumentException("User already exist");

        user = User.builder()
                .username(signupRequestDTO.getUsername())
                .email(signupRequestDTO.getEmail())
                .providerId(providerId)
                .providerType(authProviderType)
                .roles(Set.of(RoleType.PATIENT))
                .build();
        user = userRepo.save(user);

        Patient patient = Patient.builder()
            .name(signupRequestDTO.getUsername())
            .email(signupRequestDTO.getEmail())
            .user(user)
            .build();
        patientRepo.save(patient);
        
        return user;
    }

    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOauth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepo.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        String name = oAuth2User.getAttribute("name");
        String email = authUtil.determineEmailFromOAuth2User(oAuth2User, registrationId, providerId);

        User emailUser = userRepo.findByEmail(email);

        // signup user if it doesnt exist
        if (user == null && emailUser == null) {
            user = signupInternal(new SignupRequestDTO(name, null, email), providerType, providerId);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setEmail(email);
                user.setUsername(name);
                userRepo.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider" + email);
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user.getId(),
                jwtService.generateToken(user.getUsername()));
        return ResponseEntity.ok(loginResponseDTO);
    }

}
