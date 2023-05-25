package com.epam.esm.core.dto;

import com.epam.esm.core.model.entity.Receipt;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Receipt> receipts = new HashSet<>();
}
