package com.cocosongying.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cocosongying.demo.doman.Person;
import com.cocosongying.demo.repository.CustomRepository;

@Service
public class PersonQueryService {

	@Resource
	CustomRepository customRepository;

	public Page<Person> findPerson(Person person, Pageable pageable) {
		Page<Person> personPage = customRepository.findAll(new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (null != person.getId() && !"".equals(person.getId())) {
					list.add(criteriaBuilder.equal(root.get("id").as(String.class), person.getId()));
				}
				if (null != person.getName() && !"".equals(person.getName())) {
					list.add(criteriaBuilder.equal(root.get("name").as(String.class), person.getName()));
				}
				if (null != person.getAge() && !"".equals(person.getAge())) {
					list.add(criteriaBuilder.equal(root.get("age").as(String.class), person.getAge()));
				}
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		}, pageable);
		return personPage;
	}
}
