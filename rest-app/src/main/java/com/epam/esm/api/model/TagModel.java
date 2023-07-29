package com.epam.esm.api.model;

import com.epam.esm.core.dto.TagDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagModel extends RepresentationModel<TagModel> {
    @JsonUnwrapped
    private TagDTO tagDTO;
}
