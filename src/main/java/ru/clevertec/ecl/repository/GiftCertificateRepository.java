package ru.clevertec.ecl.repository;

import ru.clevertec.ecl.dto.GiftCertificateDTO;

import java.util.List;

public interface GiftCertificateRepository {
    List<GiftCertificateDTO> findAll();

    GiftCertificateDTO findById(int id);

    GiftCertificateDTO save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO update(int id, GiftCertificateDTO giftCertificateDTO);

    void delete(int id);

}
