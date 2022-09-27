package com.softserve.edu.sporthubujp.mapper;

    import org.mapstruct.Builder;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.MappingTarget;

    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailDTO;
    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;
    import com.softserve.edu.sporthubujp.entity.SubscriptionEmail;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SubscriptionEmailMapper {
    //@Mapping(target = "article.id", source = "articleId")
    SubscriptionEmail dtoToEntity(SubscriptionEmailDTO commentDTO);

    //@Mapping(target = "articleId", source = "article.id")
    SubscriptionEmailDTO entityToDto(SubscriptionEmail comment);

    //@Mapping(target = "article.id", source = "articleId")
    SubscriptionEmail dtoSaveToEntity(SubscriptionEmailSaveDTO commentSaveDTO);

    //@Mapping(target = "articleId", source = "article.id")
    SubscriptionEmailSaveDTO entityToDtoSave(SubscriptionEmail comment);
}