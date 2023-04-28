package ru.clevertec.ecl.mapper;


import lombok.Builder;
import ru.clevertec.ecl.dto.GiftCertificateDTO;
import ru.clevertec.ecl.dto.TagDTO;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.entity.Tag;

@org.mapstruct.Mapper(componentModel = "spring")

public interface Mapper {

//    @Mapping(target = "giftCertificates", ignore = true)
    TagDTO tagToTagDTO(Tag tag);

//    @Mapping(target = "giftCertificates", ignore = true)
    Tag tagDTOToTag(TagDTO tagDTO);

    GiftCertificateDTO giftCertificateToGiftCertificateDTO(GiftCertificate giftCertificate);

    GiftCertificate giftCertificateDTOToGiftCertificate(GiftCertificateDTO giftCertificateDTO);

}
