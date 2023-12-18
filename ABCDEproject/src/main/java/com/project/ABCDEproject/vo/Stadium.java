package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {

	int id;
	String name;
	int type;
	String thumbnail;
	String description;
	int rental_shoes;
	int rental_vest;
	int rental_ball;
	int sheos_price;
}
