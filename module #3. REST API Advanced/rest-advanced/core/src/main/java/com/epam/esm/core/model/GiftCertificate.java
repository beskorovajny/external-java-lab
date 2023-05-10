package com.epam.esm.core.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "gift_certificate")
public class GiftCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer duration;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    @JsonManagedReference
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = @JoinColumn(name = "gift_certificate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",  referencedColumnName = "id")
    )
    private Set<Tag> tags = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "giftCertificates")
    private Set<Receipt> receipts;

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getGiftCertificates().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getGiftCertificates().remove(this);
        }
    }
}
