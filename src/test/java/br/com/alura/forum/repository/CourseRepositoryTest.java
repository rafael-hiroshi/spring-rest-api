package br.com.alura.forum.repository;

import br.com.alura.forum.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void testShouldFindACourseByItsName() {
        String courseName = "Spring Boot";
        Course course = courseRepository.findByName(courseName);
        Assertions.assertNotNull(course);
        Assertions.assertEquals(courseName, course.getName());
    }

    @Test
    public void testShouldNotFindACourseByInvalidName() {
        String courseName = "Jpa Course 1";
        Course course = courseRepository.findByName(courseName);
        Assertions.assertNull(course);
    }

    @Test
    public void testShouldFindACourseAfterInsertingOne() {
        String name = "React JS";
        Course course = new Course(name, "Front-End");
        em.persist(course);

        course = courseRepository.findByName(name);
        Assertions.assertNotNull(course);
    }
}
