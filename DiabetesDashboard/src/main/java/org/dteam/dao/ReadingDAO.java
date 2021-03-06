package org.dteam.dao;

import java.util.ArrayList;

import org.dteam.model.Reading;

public interface ReadingDAO {
	public int addReading(Reading reading, String userID);

	public ArrayList<Reading> getReadings(String dateRange, String userID);

}
