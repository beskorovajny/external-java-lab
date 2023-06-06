package com.epam.esm.core.model.entity;

import com.epam.esm.core.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role", columnDefinition = "ENUM('GUEST', 'CUSTOMER', 'ADMINISTRATOR')")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private Set<Receipt> receipts = new HashSet<>();
}
