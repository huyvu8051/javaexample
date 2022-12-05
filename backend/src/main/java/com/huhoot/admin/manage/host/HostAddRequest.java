package com.huhoot.admin.manage.host;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HostAddRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 15)
    @Pattern(regexp = "^(admin).*$", message = "must be start with 'admin'")
    private String username;

    private Boolean isNonLocked;
}
