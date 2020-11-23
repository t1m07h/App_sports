package com.example.app_sports.Model.ActivitiesModel

data class ActivitiesData(
	val id: String,
	val title: String,
	val place: String,
	val sport: String,
	val date: String,
	val time: String,
	val level: String,
	val metadata: ActivitiesMetadata
) {
	constructor() : this("", "", "", "", "", "", "",
		ActivitiesMetadata("", "", "", "")
	){}
}