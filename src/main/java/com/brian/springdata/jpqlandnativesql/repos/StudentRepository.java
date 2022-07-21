package com.brian.springdata.jpqlandnativesql.repos;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.brian.springdata.jpqlandnativesql.entities.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	@Query("from Student")
	List<Student> findAllStudents(Pageable pageable);
	
	@Query("select firstName, lastName from Student st") // st is "alias" here and references object
	List<Object[]>findAllStudentsPartialData();
	
	@Query("from Student where firstName=:firstName")
	List<Student> findAllStudentsByFirstName(@Param("firstName")String firstName);
	
	@Query("from Student where score>:min and score <:max")
	List<Student> findStudentForGivenScores(@Param("min")int min, @Param("max") int max);
	
	@Modifying
	@Query("delete from Student where firstName =:firstName")
	void deleteStudentsByFirstName(@Param("firstName")String firstName);

	
}









