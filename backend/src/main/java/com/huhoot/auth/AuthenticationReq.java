package com.huhoot.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationReq {
	@NotNull
	@NotBlank
	@NotEmpty
	@Length(min = 4, max = 30)
	private String username;
	@NotNull
	@NotBlank
	@NotEmpty
	@Length(min = 4, max = 30)
	private String password;
}
