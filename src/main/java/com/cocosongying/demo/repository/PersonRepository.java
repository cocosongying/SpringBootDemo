package com.cocosongying.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cocosongying.demo.doman.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public Page<Person> findAll(Pageable pageable);

//	@Query("person.$name: ?#{[0]}, 'age': ?#{[1]}")
//	public Page<Person> findByNameAge(String name, String age, Pageable pageable);
	//And         --- 等价于 SQL 中的 and 关键字，     比如 findByHeightAndSex(int height,char sex)；  
	// Or         --- 等价于 SQL 中的 or 关键字，      比如 findByHeightOrSex(int height,char sex)；  
	//Between     --- 等价于 SQL 中的 between 关键字， 比如 findByHeightBetween(int min, int max)；  
	//LessThan    --- 等价于 SQL 中的 "<",           比如 findByHeightLessThan(int max)；  
	//GreaterThan --- 等价于 SQL 中的 ">",           比如 findByHeightGreaterThan(int min)；  
	//IsNull      --- 等价于 SQL 中的 "is null",     比如 findByNameIsNull()；  
	//IsNotNull   --- 等价于 SQL 中的 "is not null", 比如 findByNameIsNotNull()；  
	//NotNull     --- 与 IsNotNull 等价；  
	//Like        --- 等价于 SQL 中的 "like"，       比如 findByNameLike(String name);  
	//NotLike     --- 等价于 SQL 中的 "not like"，   比如 findByNameNotLike(String name)；  
	//OrderBy     --- 等价于 SQL 中的 "order by"，   比如 findByNameNotNullOrderByHeightAsc()；  
	//Not         --- 等价于 SQL 中的 "! ="，        比如 findByNameNot(String name)；  
	//In          --- 等价于 SQL 中的 "in"，         比如 findByNameIN(String name);  
	//NotIn       --- 等价于 SQL 中的 "not in"，     比如 findByNameNotIN(String name);  
	
	public List<Person> findByName(String name);
	
	@Query(value="select o.* from person o where o.age=?1", nativeQuery = true)
	@Modifying
	public List<Person> findOrderByAge(String age);
	
//	@SelectProvider(type = PersonProvider.class, method = "findPersonBy")
//	public Page<Person> findPersonBy(Person person, Pageable pageable);

}
