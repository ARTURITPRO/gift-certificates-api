package ru.clevertec.ecl.repository;

import ru.clevertec.ecl.dto.TagDTO;

import java.util.List;

public interface TagRepository {

    public List<TagDTO> findTagByTagName(String tagName);

    List<TagDTO> findAll();

    TagDTO findById(int id);

    TagDTO save(TagDTO tag);

    TagDTO update(int id, TagDTO tagDTO);

    void delete(int id);
}
