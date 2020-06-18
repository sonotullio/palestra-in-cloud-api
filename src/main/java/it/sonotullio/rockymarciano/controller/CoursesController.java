package it.sonotullio.rockymarciano.controller;

import it.sonotullio.rockymarciano.model.Course;
import it.sonotullio.rockymarciano.repository.CourseRepository;
import it.sonotullio.rockymarciano.utils.DateUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoursesController {

    @Autowired
    CourseRepository courseRepository;

    @PostMapping
    public Course save(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public Course update(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping
    public Collection<Course> getAll(String date) throws ParseException {
        if (date != null) {
            Date dateDate = DateUtils.parse(date, "yyyy-MM-dd");
            return courseRepository.findAllByDate(dateDate);
        }
        return (Collection<Course>) courseRepository.findAll();
    }

    @GetMapping(value = "/template", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] downloadTemplate() throws IOException {
        InputStream inp = getClass().getClassLoader().getResourceAsStream("exceltemplate/courses_plan_template.xlsx");

        Workbook wb = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            wb = WorkbookFactory.create(inp);
            Iterator<Sheet> sheetIterator = wb.sheetIterator();

            //core..
            int index = 0;
            while (sheetIterator.hasNext()) {
                sheetIterator.next();
                wb.setSheetName(index, dateFormat.format(date));

                calendar.add(Calendar.DATE, 1);
                date = calendar.getTime();
                index ++;
            }

            wb.write(output);
        } finally {
            if (wb!=null) {
                wb.close();
            }
        }
        return output.toByteArray();
    }

    @PostMapping("/import")
    public Collection<Course> uploadCourses(@RequestParam(value = "file") MultipartFile file) throws IOException, ParseException {
        InputStream is = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(is);

        Collection<Course> courses = Course.parse(workbook);

        for (Course course : courses) {
            Optional<Course> result = courseRepository.findByDateAndStartTimeAndSport(course.getDate(), course.getStartTime(), course.getSport());
            if (result.isPresent()) {
                course.setId(result.get().getId());
            }
            courseRepository.save(course);
        }

        return courses;
    }
}
