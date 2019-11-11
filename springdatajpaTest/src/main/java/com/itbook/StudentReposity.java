package com.itbook;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)
public interface StudentReposity { /*extends Repository<Student, Integer> {*/

    Student findByName(String name);

    @Query("select o from Student o where age > :age")
    List<Student> getStudents(@Param("age") Integer age);

    @Modifying
    @Query("update Student o set o.age = :age where o.id = :id")
    void update(@Param("id") Integer id, @Param("age") Integer age);
}
