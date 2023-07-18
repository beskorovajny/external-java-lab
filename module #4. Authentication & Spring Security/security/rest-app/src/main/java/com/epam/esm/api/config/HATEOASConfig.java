package com.epam.esm.api.config;

import com.epam.esm.core.dto.GiftCertificateDTO;
import com.epam.esm.core.dto.ReceiptDTO;
import com.epam.esm.core.dto.TagDTO;
import com.epam.esm.core.dto.UserDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan("com.epam.esm")
@EnableSpringDataWebSupport
public class HATEOASConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        HateoasPageableHandlerMethodArgumentResolver resolver = customPageableResolver(customSortResolver());
        resolver.setMaxPageSize(100);
        resolvers.add(resolver);
    }

    @Bean
    public @NotNull HateoasSortHandlerMethodArgumentResolver customSortResolver() {
        HateoasSortHandlerMethodArgumentResolver sortArgumentResolver = new HateoasSortHandlerMethodArgumentResolver();

        Sort defaultSort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));

        sortArgumentResolver.setFallbackSort(defaultSort);

        return sortArgumentResolver;
    }

    @Bean
    public HateoasPageableHandlerMethodArgumentResolver customPageableResolver(
            HateoasSortHandlerMethodArgumentResolver sortResolver) {
        return new HateoasPageableHandlerMethodArgumentResolver(sortResolver);
    }

    @Bean
    public PagedResourcesAssembler<GiftCertificateDTO> certificatePagedResourcesAssembler(
            HateoasPageableHandlerMethodArgumentResolver pageableResolver) {
        return new PagedResourcesAssembler<>(pageableResolver, null);
    }

    @Bean
    public PagedResourcesAssembler<TagDTO> tagPagedResourcesAssembler(
            HateoasPageableHandlerMethodArgumentResolver pageableResolver) {
        return new PagedResourcesAssembler<>(pageableResolver, null);
    }

    @Bean
    public PagedResourcesAssembler<ReceiptDTO> receiptPagedResourcesAssembler(
            HateoasPageableHandlerMethodArgumentResolver pageableResolver) {
        return new PagedResourcesAssembler<>(pageableResolver, null);
    }

    @Bean
    public PagedResourcesAssembler<UserDTO> userPagedResourcesAssembler(
            HateoasPageableHandlerMethodArgumentResolver pageableResolver) {
        return new PagedResourcesAssembler<>(pageableResolver, null);
    }

}
