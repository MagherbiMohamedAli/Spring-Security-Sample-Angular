package tn.biramgroup.pointage.springSecurityJwt;

import lombok.Getter;
import lombok.Setter;
import tn.biramgroup.pointage.model.Role;

import java.util.Set;

@Getter
@Setter
public class AuthResponse {
    private String email;
    private String accessToken;
    private Long id;
    private String username;
    private Set<Role> roles;

    public AuthResponse(String email, String accessToken, Long id, String username, Set<Role> roles) {
        this.email = email;
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

}
