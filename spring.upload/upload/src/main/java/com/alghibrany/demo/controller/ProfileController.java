package com.alghibrany.demo.controller;

import com.alghibrany.demo.model.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.alghibrany.demo.dao.ProfileDao;

/**
 *
 * @author ahmad
 */
@Controller
public class ProfileController {

    @Autowired
    ProfileDao pd;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/person/create")
    public String index(Model model) {
        model.addAttribute("persons", new Profile());
        return "index";
    }

    @RequestMapping("/{id}")
    public String home(Model model, @PathVariable int id) {
        model.addAttribute("persons", pd.findById(id));
        return "home";
    }

    @PostMapping("/profile/create")
    public String create(Model model, Profile profile, @RequestParam("file") MultipartFile[] files) {
        Profile ap = pd.save(profile);
        int id = ap.getId();
        for (MultipartFile multi : files) {
            if (multi.getContentType().toString().contains("image/") && !multi.isEmpty()) {
                try {
                    profile.setData_gambar(multi.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("" + id).path("/img/")
                        .path("foto.jpg").toUriString();
                profile.setLink_gambar(fileDownloadUri);
            } else if (multi.getContentType().toString().equals("application/pdf") && !multi.isEmpty()) {
                try {
                    profile.setData_cv(multi.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("" + id).path("/pdf/")
                        .path("cv.pdf").toUriString();
                profile.setLink_cv(fileDownloadUri);
            }
        }

        model.addAttribute("profile", pd.save(profile));

        return "redirect:/profile/create";
    }

    @GetMapping("/{id}/img/foto.jpg")
    public ResponseEntity downloadImg(@PathVariable String id) {
        Optional<Profile> op = pd.findById(Integer.parseInt(id));
        Profile p = op.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "foto.jpg" + "\"")
                .body(p.getData_gambar());
    }

    @GetMapping("/{id}/pdf/cv.pdf")
    public ResponseEntity downloadPdf(@PathVariable String id) {
        Optional<Profile> op = pd.findById(Integer.parseInt(id));
        Profile p = op.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "cv.pdf" + "\"")
                .body(p.getData_cv());
    }

}
