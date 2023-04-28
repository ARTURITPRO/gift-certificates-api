package ru.clevertec.ecl.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.GiftCertificateDTO;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.repository.GiftCertificateRepository;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.clevertec.ecl.constants.Constants.EXCEPTION_MESSAGE_ENTITY_NOT_FOUND_FORMAT;

@Slf4j
@RequiredArgsConstructor
@Repository
@Transactional
public class GiftCertificateRepositoryImpl implements GiftCertificateRepository {

    private final Mapper mapper;

    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<GiftCertificateDTO> findAll() {
        log.info("execution found giftCertificate...");
        List<GiftCertificate> giftCertificates = sessionFactory
                .getCurrentSession()
                .getNamedQuery("GiftCertificate.findAllWithTag")
                .list();
        log.info("successful found giftCertificate - {}", giftCertificates);

        return giftCertificates.stream().map(mapper::giftCertificateToGiftCertificateDTO).toList();
    }

    @Transactional(readOnly = true)
    public GiftCertificateDTO findById(int id) {
        log.info("execution found giftCertificate - {}...", id);

        GiftCertificate giftCertificate = (GiftCertificate) sessionFactory
                .getCurrentSession()
                .getNamedQuery("GiftCertificate.findById")
                .setParameter("id", id)
                .uniqueResult();

        final GiftCertificateDTO dto = mapper.giftCertificateToGiftCertificateDTO(giftCertificate);
        log.info("successful found giftCertificate - {}", dto);
        return dto;
    }


    public GiftCertificateDTO save(GiftCertificateDTO giftCertificate) {
        log.info("gift certificate to save to database - {}", giftCertificate);
        GiftCertificate giftCertificate1 = mapper.giftCertificateDTOToGiftCertificate(giftCertificate);
        Integer idGiftCertificate = (int) sessionFactory.getCurrentSession().save(giftCertificate1);
        GiftCertificate giftCertificate2 = sessionFactory.getCurrentSession().get(GiftCertificate.class, idGiftCertificate);
        GiftCertificateDTO giftCertificateDTO = mapper.giftCertificateToGiftCertificateDTO(giftCertificate2);
        log.info("Singer saved with id: " + giftCertificate.getId());
        log.info("successful saving of the gift certificate in the database - {}", giftCertificateDTO);
        return giftCertificateDTO;
    }

    @Transactional
    public void delete(int id) {
        GiftCertificate giftCertificate = sessionFactory.getCurrentSession().get(GiftCertificate.class, id);
        sessionFactory.getCurrentSession().delete(giftCertificate);
        log.info("successful gift certificate with id = {} deleted successfully", id);
    }

    @Transactional
    public GiftCertificateDTO update(int id, GiftCertificateDTO giftCertificateDTO) {
        log.info("gift certificate for updating in the database - {}", giftCertificateDTO);
        final Optional<GiftCertificateDTO> updated = Optional
                .ofNullable(Optional
                        .of(sessionFactory.getCurrentSession().find(GiftCertificate.class, id))
                        .map(giftCertificate -> updateGiftCertificateFromGiftCertificateDTO(giftCertificate, giftCertificateDTO))
                        .map(sessionFactory.getCurrentSession()::merge)
                        .map(object -> (GiftCertificate) object)
                        .map(mapper::giftCertificateToGiftCertificateDTO)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE_ENTITY_NOT_FOUND_FORMAT, id))));
        log.info("successful update of the gift certificate in the database - {}", updated);
        return updated.get();
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
