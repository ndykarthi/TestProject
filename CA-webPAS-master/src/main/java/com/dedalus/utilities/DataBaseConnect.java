package com.dedalus.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseConnect {
	
	public ResultSet getDatabaseResult(String sqlStr) throws ClassNotFoundException, SQLException;
	

}
