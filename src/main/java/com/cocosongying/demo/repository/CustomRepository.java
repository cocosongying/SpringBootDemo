package com.cocosongying.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cocosongying.demo.doman.Person;

@Repository("customRepository")
public interface CustomRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
