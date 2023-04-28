package ru.clevertec.ecl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.config.AdvancedConfig;
import ru.clevertec.ecl.dto.TagDTO;
import ru.clevertec.ecl.entity.Tag;
import ru.clevertec.ecl.service.GiftCertificateService;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> fidAll() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    public TagDTO getById(@PathVariable("id") int id) {
        return tagService.findById(id);
    }

    @PostMapping
    public TagDTO save(@RequestBody TagDTO tag) {
        return tagService.save(tag);
    }

    @PutMapping("/{id}")
    public TagDTO update(@PathVariable("id") int id, @RequestBody TagDTO tag) {
        return tagService.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        tagService.delete(id);
        return ResponseEntity.ok("Tag deleted successfully");
    }
    @GetMapping("/tag")
    public List<TagDTO> findByTagName(@RequestParam("tag_name") String tagName){
        return tagService.findLstTagByTagName(tagName);
    }

}
