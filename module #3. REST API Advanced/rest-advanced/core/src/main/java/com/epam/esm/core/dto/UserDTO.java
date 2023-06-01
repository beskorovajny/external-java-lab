package com.epam.esm.core.dto;

import com.epam.esm.core.model.entity.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    private Set<Receipt> receipts;
}
