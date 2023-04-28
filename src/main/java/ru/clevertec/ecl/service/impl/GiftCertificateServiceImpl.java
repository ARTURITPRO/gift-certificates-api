package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.GiftCertificateDTO;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.repository.GiftCertificateRepository;
import ru.clevertec.ecl.service.GiftCertificateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GiftCertificateServiceImpl implements GiftCertificateService {

    @Autowired
    private final GiftCertificateRepository giftCertificateRepository;

    @Override
    public List<GiftCertificateDTO> findAll() {
        return giftCertificateRepository.findAll();
    }

    @Override
    public GiftCertificateDTO findById(int id) {
        GiftCertificateDTO dto = giftCertificateRepository.findById(id);
        log.info("found giftCertificate - {}", dto);
        return dto;
    }


    @Override
    @Transactional
    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) {
        log.info("gift certificate to save to database - {}", giftCertificate);
        GiftCertificateDTO saved = giftCertificateRepository.save(giftCertificate);
        log.info("successful saving of the gift certificate in the database - {}", saved);
        return saved;
    }

    @Override
    @Transactional
    public GiftCertificateDTO update(int id, GiftCertificateDTO giftCertificateDTO) {
        log.info("gift certificate for updating in the database - {}", giftCertificateDTO);
        GiftCertificateDTO updated = giftCertificateRepository.update(id, giftCertificateDTO);

        log.info("successful update of the gift certificate in the database - {}", updated);
        return updated;
    }

    @Override
    @Transactional
    public void delete(int id) {
        giftCertificateRepository.delete(id);
        log.info("gift certificate with id = {} deleted successfully", id);
    }

    private GiftCertificate updateGiftCertificateFromGiftCertificateDTO(GiftCertificate certificate, GiftCertificateDTO dto) {
        if (Objects.nonNull(dto.getName())) {
            certificate.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getDescription())) {
            certificate.setDescription(dto.getDescription());
        }
        if (Objects.nonNull(dto.getPrice())) {
            certificate.setPrice(dto.getPrice());
        }
        if (Objects.nonNull(dto.getDuration())) {
            certificate.setDuration(dto.getDuration());
        }
        if (Objects.nonNull(dto.getCreateDate())) {
            certificate.setCreateDate(dto.getCreateDate());
        }

        certificate.setLastUpdateDate(LocalDateTime.now());
        return certificate;
    }
}
