package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Course;
import it.sonotullio.rockymarciano.repository.CourseRepository;
import it.sonotullio.rockymarciano.utils.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping(value = "/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoursesController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public Collection<Course> getAll(String date) throws ParseException {
        if (date != null) {
            Date dateDate = DateUtils.parse(date, "yyyy-MM-dd");
            return courseRepository.findAllByDate(dateDate);
        }
        return (Collection<Course>) courseRepository.findAll();
    }

    @PostMapping("/import")
    public Collection<Course> uploadCourses(@RequestParam(value = "file") MultipartFile file) throws IOException, ParseException {
        InputStream is = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(is);

        Collection<Course> courses = Course.parse(workbook);

        for (Course course : courses) {
            courseRepository.save(course);
        }

        return courses;
    }
}
