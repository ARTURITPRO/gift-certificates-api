package ru.clevertec.ecl.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.GiftCertificateDTO;
import ru.clevertec.ecl.dto.TagDTO;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.entity.Tag;
import ru.clevertec.ecl.mapper.Mapper;
import ru.clevertec.ecl.repository.TagRepository;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.clevertec.ecl.constants.Constants.EXCEPTION_MESSAGE_ENTITY_NOT_FOUND_FORMAT;

@Slf4j
@RequiredArgsConstructor
@Repository
@Transactional
public class TagRepositoryImpl implements TagRepository {

    private SessionFactory sessionFactory;

    private final Mapper mapper;

    @Override
    public List<TagDTO> findTagByTagName(String tagName) {
        log.info("execution found list of gift certificates with tag name = {}...", tagName);
        final List<Tag> listTag = sessionFactory.getCurrentSession()
                .getNamedQuery("Tag.findTagByTagName")
                .setParameter("tagName", tagName)
                .getResultList();

        log.info("!!!!!!!!! list Tag = " + listTag.isEmpty());
        if (!listTag.isEmpty()) {
            final List<TagDTO> collect = listTag.stream()
                    .map(mapper::tagToTagDTO)
                    .collect(Collectors.toList());
            log.info(" successful found list of TagDTO with tag name = {}. TagDTO list - {}", tagName, collect);
            return collect;
        }
        throw new IllegalArgumentException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDTO> findAll() {
        log.info("execution findAll()...");
        List <Tag> tagDto =  sessionFactory.getCurrentSession().createQuery("from Tag t").list();
        List<TagDTO> tagDTOList = tagDto.stream().map(mapper::tagToTagDTO).collect(Collectors.toList());
        log.info("successful findAll()" );
        return tagDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public TagDTO findById(int id) {
        log.info("execution findById tag with id =  {}...", id);
        final TagDTO tagDto = Optional.of(sessionFactory.getCurrentSession().get(Tag.class,id)).map(mapper::tagToTagDTO)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE_ENTITY_NOT_FOUND_FORMAT, id)));
        log.info("found tag - {}", tagDto);
        return tagDto;
    }

    @Override
    @Transactional
    public TagDTO save(TagDTO tagDto1) {
        log.info("execution to save tag  to database - {}...", tagDto1);
        Tag tag1 = mapper.tagDTOToTag(tagDto1);
        Integer idTag = (int)sessionFactory.getCurrentSession().save(tag1);
        Tag tag  = sessionFactory.getCurrentSession().get(Tag.class,idTag);
        TagDTO tagDto = mapper.tagToTagDTO(tag);
        log.info("successful saving of the tag in the database - {}", tagDto);
        return tagDto;
    }

    @Override
    @Transactional
    public TagDTO update(int id, TagDTO tagDTO) {
        log.info("execution for updating tag  in the database - {}...", tagDTO);
        final Optional <TagDTO> updated = Optional
                .ofNullable(Optional
                .of(sessionFactory.getCurrentSession().get(Tag.class,id))
                .map(tag -> updateTagFromTagDTO(tag, tagDTO))
                .map(mapper::tagToTagDTO)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE_ENTITY_NOT_FOUND_FORMAT, id))));
        log.info("successful update of the tag in the database - {}",updated);
        return updated.get();
    }


    @Override
    @Transactional
    public void delete(int id) {
        log.info("execution deleted tag with id = {}...", id);
        Tag tag = sessionFactory.getCurrentSession().get(Tag.class, id);
        sessionFactory.getCurrentSession().remove(tag);
        log.info("successful deleted tag = {}", tag);
    }

    private Tag updateTagFromTagDTO(Tag tag, TagDTO dto) {
        if (Objects.nonNull(dto.getName())) {
            tag.setName(dto.getName());
        }
        return tag;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
