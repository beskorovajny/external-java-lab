package com.epam.esm.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true, nullable = false)
    private String name;
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "tags")
    private Set<GiftCertificate> giftCertificates = new HashSet<>();

    public void addCertificate(GiftCertificate giftCertificate) {
        this.giftCertificates.add(giftCertificate);
        giftCertificate.getTags().add(this);
    }

    public void removeCertificate(GiftCertificate giftCertificate) {
        this.giftCertificates.remove(giftCertificate);
        giftCertificate.getTags().remove(this);
    }
}