package com.app.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass  //inheritance in entity relationship
public class BaseEntity {
	// primary key
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

//=>using LOMBOK will reduce this boiler plate code 
//	public Integer getId() {
//		return Id;
//	}
//
//	public void setId(Integer id) {
//		Id = id;
//	}

}
