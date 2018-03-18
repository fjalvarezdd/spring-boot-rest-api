package com.fjalvarez.core;

import com.fjalvarez.course.Course;
import com.fjalvarez.course.CourseRepository;
import com.fjalvarez.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] templates = {
                "Up and running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Neckbeards",
                "Under the hood: %s"
        };

        String[] buzzwords = {
                "Spring REST Data",
                "Java 9",
                "Scala",
                "Groovy",
                "Hibernate"
        };

        List<Course> bunchOfCourses = new ArrayList<Course>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course course = new Course(title, "http://example.com" );
                    course.addReview(new Review(i % 5, String.format("Moar %s please!!!", buzzword)));
                    bunchOfCourses.add(course);
                });
        courses.save(bunchOfCourses);
    }
}
