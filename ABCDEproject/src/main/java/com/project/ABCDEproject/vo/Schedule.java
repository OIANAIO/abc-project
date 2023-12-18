package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	int id;
	int stadium_id;
	int owner_id;
	int start_time;
	int end_time;
	int price;
	String game_date;
}
