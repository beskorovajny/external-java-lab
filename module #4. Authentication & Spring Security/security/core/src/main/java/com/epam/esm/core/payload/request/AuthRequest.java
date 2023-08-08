package com.epam.esm.core.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Size(min = 3, max = 250, message = "Email length must be between 3 and 250 symbols")
    @NotBlank(message = "User's eMail is mandatory!")
    private String email;

    @Size(min = 8, message = "Password length requires a minimum of 8 symbols")
    @NotBlank(message = "Can you envision a login process that does not require a password?")
    private String password;
}
