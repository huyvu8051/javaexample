package com.huhoot.auth;

import com.huhoot.config.mvc.CustomBodyResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@CustomBodyResponse
@Builder
@Getter
public class AuthenticationResp {
	private final String jwt;
	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;
}
