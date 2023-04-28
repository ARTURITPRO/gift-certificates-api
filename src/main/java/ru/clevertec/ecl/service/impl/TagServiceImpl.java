package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.TagDTO;
import ru.clevertec.ecl.repository.TagRepository;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    @Autowired
    private final TagRepository tagRepository;

    @Override
    public List<TagDTO> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public TagDTO findById(int id) {
        final TagDTO dto = tagRepository.findById(id);
        log.info("found tag - {}", dto);
        return dto;
    }

    @Override
    public List<TagDTO> findLstTagByTagName(String tagName) {
        List<TagDTO> collect = tagRepository.findTagByTagName(tagName);
        log.info("Found list of gift certificates with tag name = {}. Gift certificate list - {}", tagName, collect);
        return collect;
    }

    @Override
    @Transactional
    public TagDTO save(TagDTO tag) {
        log.info("tag to save to database - {}", tag);
        final TagDTO saved = tagRepository.save(tag);
        log.info("successful saving of the tag in the database - {}", saved);
        return saved;
    }

    @Override
    public TagDTO update(int id, TagDTO tagDTO) {
        log.info("tag for updating in the database - {}", tagDTO);
        final TagDTO updated = tagRepository.update(id, tagDTO);
        log.info("successful update of the tag in the database - {}", updated);
        return updated;
    }

    @Override
    public void delete(int id) {
        tagRepository.delete(id);
        log.info("tag with id = {} deleted successfully", id);
    }
}
