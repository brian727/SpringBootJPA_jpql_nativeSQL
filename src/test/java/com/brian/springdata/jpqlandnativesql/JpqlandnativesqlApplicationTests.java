package com.brian.springdata.jpqlandnativesql;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.brian.springdata.jpqlandnativesql.entities.Student;
import com.brian.springdata.jpqlandnativesql.repos.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlandnativesqlApplicationTests {
	
	@Autowired
	StudentRepository repository;
	
	@Test
	public void testStudentCreate() {
		Student student = new Student();
		student.setFirstName("Brian");
		student.setLastName("sdfsdfsdf");
		student.setScore(99);
		
		Student student2 = new Student();
		student2.setFirstName("Rocket");
		student2.setLastName("asdasdasd");
		student2.setScore(88);
		
		repository.save(student);
		repository.save(student2);
	}
	
	@Test
	public void testFindAllStudents() {
		System.out.println(repository.findAllStudents(PageRequest.of(0,5, Direction.DESC,"id")));
	}
	
	

	@Test
	public void testFindAllStudentsPartial() {
		List<Object[]> partialData = repository.findAllStudentsPartialData();
		for (Object[] objects : partialData) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}
	
	@Test
	public void testFindAllStudentsByFirstName() {
		System.out.println(repository.findAllStudentsByFirstName("Brian"));
	}
	
	@Test
	public void testFindAllStudentsByScores() {
		System.out.println(repository.findStudentForGivenScores(80,100));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteStudentByFirstName() {
		repository.deleteStudentsByFirstName("Brian");
	}
	
	@Test
	public void testFindAllStudentNQ() {
		System.out.println(repository.findAllStudentNQ());
	}
	
	@Test
	public void testFindByFirstNameNQ() {
		System.out.println(repository.findByFirstNQ("Rocket"));
	}
	
}






















