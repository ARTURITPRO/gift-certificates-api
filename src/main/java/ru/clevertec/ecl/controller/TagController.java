package ru.clevertec.ecl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dto.TagDTO;
import ru.clevertec.ecl.service.TagService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {
//            GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AdvancedConfig.class);
//    TagService tagService = ctx.getBean(TagService.class);
//TagDTO t  = tagController.getById(1);
//log.info(t.toString());
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        System.out.println("public TagController(TagService tagService)");
        log.info("public TagController(TagService tagService)");
        this.tagService = tagService;
    }

    @GetMapping("/hello-world")
    public String sayHello (){
        return "Hello-World";
    }


    @GetMapping
    public List<TagDTO> fidAll() {
        System.out.println("!!!!!!!!!!public List<TagDTO> fidAll()");
        log.info("!!!!!!!!!!"+"public List<TagDTO> fidAll()");
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
