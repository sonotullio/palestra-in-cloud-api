package it.sonotullio.rockymarciano.repository;

import it.sonotullio.rockymarciano.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    Optional<Course> findByDateAndStartTimeAndSport(Date date, Date startTime, String sport);
    Collection<Course> findAllByDate(Date date);

}
