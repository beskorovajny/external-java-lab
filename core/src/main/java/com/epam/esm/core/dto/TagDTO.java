package com.epam.esm.core.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Long id;

    @Size(min = 1, max = 45, message = "Tag title length must be between 1 and 45 symbols")
    @NotEmpty(message = "Tag title is mandatory!")
    private String name;
}
