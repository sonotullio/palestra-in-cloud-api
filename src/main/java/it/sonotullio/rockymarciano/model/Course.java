package it.sonotullio.rockymarciano.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.sonotullio.rockymarciano.utils.DateUtils;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.util.*;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    @Temporal(TemporalType.DATE)
    Date date;

    Date startTime;
    Date finishTime;

    String sport;
    double duration = 1;
    int prenotation = 0;
    int prenotationMax;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    public static Collection<Course> parse(Workbook workbook) throws ParseException {
        Collection<Course> courses = new ArrayList<>();

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            Date date = DateUtils.parse(sheet.getSheetName(), "yyyy-MM-dd");

            Iterator<Row> rowIterator = sheet.rowIterator();

            // Skip Header
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String time = Double.toString(row.getCell(0).getNumericCellValue());
                String timeFinish = Double.toString(row.getCell(1).getNumericCellValue());

                String hours = time.split("\\.")[0];
                String minute = time.split("\\.")[1];

                String hoursFinish = timeFinish.split("\\.")[0];
                String minuteFinish = timeFinish.split("\\.")[1];

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1900 + date.getYear());
                calendar.set(Calendar.MONTH, date.getMonth());
                calendar.set(Calendar.DATE, date.getDate() -1);

                calendar.set(Calendar.HOUR, new Integer(hours));
                calendar.set(Calendar.MINUTE, new Integer(minute));

                Date startTime = calendar.getTime();

                calendar.set(Calendar.HOUR, new Integer(hoursFinish));
                calendar.set(Calendar.MINUTE, new Integer(minuteFinish));

                Date finishTime = calendar.getTime();

                String sport = row.getCell(2).getStringCellValue();
                int limit = (int) row.getCell(3).getNumericCellValue();

                Course course = new Course();
                course.setDate(calendar.getTime());
                course.setStartTime(startTime);
                course.setFinishTime(finishTime);
                course.setSport(sport);
                course.setPrenotationMax(limit);

                courses.add(course);

            }
        }

        return courses;
    }

}
