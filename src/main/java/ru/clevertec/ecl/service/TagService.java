package ru.clevertec.ecl.service;

import ru.clevertec.ecl.dto.TagDTO;

import java.util.List;

public interface TagService {

    public List<TagDTO> findLstTagByTagName(String tagName);
    List<TagDTO> findAll();

    TagDTO findById(int id);

    TagDTO save(TagDTO tag);

    TagDTO update(int id, TagDTO tagDTO);

    void delete(int id);
}
