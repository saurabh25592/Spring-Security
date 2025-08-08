package com.Auth.RoleBasedAuthorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.RoleBasedAuthorization.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
