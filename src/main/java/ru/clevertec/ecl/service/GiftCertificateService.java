package ru.clevertec.ecl.service;

import ru.clevertec.ecl.dto.GiftCertificateDTO;

import java.util.List;

public interface GiftCertificateService {

    List<GiftCertificateDTO> findAll();

    GiftCertificateDTO findById(int id);

    GiftCertificateDTO save(GiftCertificateDTO giftCertificate);

    GiftCertificateDTO update(int id, GiftCertificateDTO giftCertificateDTO);

    void delete(int id);

}
