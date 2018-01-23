package com.cocosongying.demo.doman;

import org.apache.ibatis.jdbc.SQL;

public class PersonProvider {
	public String findPersonBy(Person person) {
		return new SQL(){{
			SELECT("id, name, age");
			FROM("person");
			if(person.getId()!=null && !"".equals(person.getId())){
				WHERE("id = #{id}");
			}
			if(person.getName()!=null && !"".equals(person.getName())){
				WHERE("name = #{name}");
			}
			if(person.getAge()!=null && !"".equals(person.getAge())){
				WHERE("age = #{age}");
			}
		}}.toString();
	}
}
