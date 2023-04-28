package ru.clevertec.ecl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.config.AdvancedConfig;
import ru.clevertec.ecl.dto.GiftCertificateDTO;

import ru.clevertec.ecl.service.GiftCertificateService;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gift")
public class GiftCertificateController {

    GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    @ResponseBody
    public List<GiftCertificateDTO> findAll() {
     return giftCertificateService.findAll();
    }

    @GetMapping("/{id}")
    public GiftCertificateDTO findById(@PathVariable("id") int id) {
        return giftCertificateService.findById(id);
    }

    @PostMapping
    public GiftCertificateDTO save(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.save(giftCertificateDTO);
    }


    @PutMapping("/{id}")
    public GiftCertificateDTO update(@PathVariable("id") int id, @RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.update(id, giftCertificateDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        giftCertificateService.delete(id);
        return ResponseEntity.ok("Gift certificate deleted successfully");
    }
}
