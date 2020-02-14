package com.alghibrany.demo.controller;

import com.alghibrany.demo.dao.PersonDao;
import com.alghibrany.demo.model.Person;

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


/**
 *
 * @author ahmad
 */
@Controller
public class PersonController {

    private static final String UPLOADED_FOLDER = "./src/main/resources/static/image/";
    private static final String UPLOADED_FOLDERPDF = "./src/main/resources/static/pdf/";

    @Autowired
    PersonDao pd;

    @RequestMapping("/")
    public String home() {
        return "home";
    }
    @RequestMapping("/person/create")
    public String index(Model model) {
        model.addAttribute("persons", new Person());
        return "index";
    }
    
    @RequestMapping("/{id}")
    public String home(Model model, @PathVariable int id) {
        model.addAttribute("persons", pd.findImg(id));
        return "home";
    }    

    @PostMapping("/person/create")
    public String create(Model model, Person person, @RequestParam("file") MultipartFile[] files, @RequestParam("file") MultipartFile file) throws IOException {
        Person p = pd.save(person);
        int id = p.getId();
        byte[] bytes = file.getBytes();
        for (MultipartFile multi : files) {
            if (multi.getContentType().toString().contains("image/") && !multi.isEmpty()) {
                try {
                    person.setData_gambar(multi.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("" + id).path(UPLOADED_FOLDER)
                        .path("foto.jpg").toUriString();
                person.setLink_gambar(fileDownloadUri);
            } else if (multi.getContentType().toString().equals("application/pdf") && !multi.isEmpty()) {
                try {
                    person.setData_cv(multi.getOriginalFilename());
                    Path path = Paths.get(UPLOADED_FOLDERPDF + file.getOriginalFilename());
            Files.write(path, bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("" + id).path(UPLOADED_FOLDERPDF)
                        .path("cv.pdf").toUriString();
                person.setLink_cv(fileDownloadUri);
            }
        }

        model.addAttribute("persons", pd.save(person));

        return "redirect:/person/create";
    }

    @GetMapping("/{id}/img/foto.jpg")
    public ResponseEntity downloadImg(@PathVariable String id) {
        Optional<Person> op = pd.findById(Integer.parseInt(id));
        Person p = op.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "foto.jpg" + "\"")
                .body(p.getData_gambar());
    }

    @GetMapping("/{id}/pdf/cv.pdf")
    public ResponseEntity downloadPdf(@PathVariable String id) {
        Optional<Person> op = pd.findById(Integer.parseInt(id));
        Person p = op.get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "cv.pdf" + "\"")
                .body(p.getData_cv());
    }

}
