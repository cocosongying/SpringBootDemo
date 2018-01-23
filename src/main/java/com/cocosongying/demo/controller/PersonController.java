package com.cocosongying.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cocosongying.demo.doman.Person;
import com.cocosongying.demo.doman.Result;
import com.cocosongying.demo.repository.CustomRepository;
import com.cocosongying.demo.repository.PersonRepository;
import com.cocosongying.demo.service.PersonQueryService;
import com.cocosongying.demo.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CustomRepository customRepository;
	
	@Autowired
	private PersonQueryService personQueryService;
	
	@GetMapping(value="/person")
	public Result<List<Person>> getPersonList(HttpServletRequest request, HttpServletResponse response) {
		return ResultUtil.success(personRepository.findAll());
	}

	@PostMapping(value="/person")
	public Result addPerson(Person person) {
		return ResultUtil.success(personRepository.save(person));
	}
	
	@GetMapping(value="/person/{id}")
	public Result getPersonById(@PathVariable("id") Integer id) {
		return ResultUtil.success(personRepository.findOne(id));
	}
	
	@DeleteMapping(value="/person/{id}")
	public Result deletePersonById(@PathVariable("id") Integer id) {
		personRepository.delete(id);
		return ResultUtil.success();
	}
	
	@PutMapping(value="/person/{id}")
	public Result updatePerson(Person person) {
		return ResultUtil.success(personRepository.save(person));
	}
	
	@GetMapping(value="/person/name/{name}")
	public Result getPersonByName(@PathVariable("name") String name) {
		return ResultUtil.success(personRepository.findByName(name));
	}
	
	@GetMapping(value="/person/age/{age}")
	public Result getPersonByAge(@PathVariable("age") String age) {
		return ResultUtil.success(personRepository.findOrderByAge(age));
	}
	
	@RequestMapping(value = "/person/page",method = RequestMethod.GET)
	public Result getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
		Page<Person> pagePerson = personRepository.findAll(new PageRequest(page, size));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pagePerson.getSize());
		map.put("rows", pagePerson.getContent());
		return ResultUtil.success(map);
	}
	
	@RequestMapping(value = "/person/search",method = RequestMethod.GET)
	public Map<String, Object> getSearchData(
			@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "sortName", defaultValue = "id") String sortName,
			@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "age", required = false) String age){
		Map<String, Direction> mapSort = new HashMap<String, Direction>();
		mapSort.put("desc", Direction.DESC);
		mapSort.put("asc", Direction.ASC);
		
		Sort sort = new Sort(mapSort.get(sortOrder), sortName);
		Pageable pageable = new PageRequest(offset, limit, sort);
		Person person = new Person();
		person.setName(name);
		if(!"".equals(age)){
			person.setAge(Integer.valueOf(age));
		}
		
//		Page<Person> pagePerson = personRepository.findByNameAge(name, age, new PageRequest(offset, limit));
//		Page<Person> pagePerson = personRepository.findAll(pageable);
		Page<Person> pagePerson = personQueryService.findPerson(person, pageable);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pagePerson.getTotalElements());
		map.put("rows", pagePerson.getContent());
//		map.put("total", pagePerson.getSize());
//		map.put("rows", pagePerson.getContent());
//		return ResultUtil.success(map);
		return map;
	}
}
