package com.dedalus.utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dedalus.restasssured.RESTAssuredBase;

public class DataBaseUtility extends RESTAssuredBase {

	public List<Object> getResultSetList(ResultSet rs) throws ClassNotFoundException, SQLException {

		List<Object> list = new ArrayList();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			// get column names
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCount = rsMeta.getColumnCount();
			List<String> columnNames = new ArrayList<String>();

			// Loop to get all column names
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(rsMeta.getColumnName(i).toUpperCase());
			}
			
			for (int i = 1; i <= columnCount; i++) {

				String key = columnNames.get(i - 1);
				String value = rs.getString(i);
				map.put(key, value);
			}
			for(Entry<Object, Object> entry: map.entrySet()) {
		        System.out.println(entry.getKey());
		        System.out.println(entry.getValue());
		        list.add(entry.getValue());
		    }
			//list.add(map);
			
			// get row count
			while (rs.next()) {
				HashMap<Object, Object> hMap = new HashMap<Object, Object>();
				for (int i = 1; i <= columnCount; i++) {

					String key = columnNames.get(i - 1);
					String value = rs.getString(key);
					hMap.put(key, value);
					}
				for(Entry<Object, Object> entry: hMap.entrySet()) {
			        System.out.println(entry.getKey());
			        System.out.println(entry.getValue());
			        list.add(entry.getValue());
			    }
				//list.add(hMap);
			}
			
		
		} catch (Exception e) {
			System.out.println("resultSet() not formatted");
			e.printStackTrace();
		}
					finally {
			//setConnection.close();
			//return null;
		}
		
            return list;
	}




	
}
