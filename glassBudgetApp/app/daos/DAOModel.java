package daos;

import java.sql.SQLException;
import java.util.List;

public interface DAOModel <T>{
	
	void save() throws SQLException;
	void delete() throws SQLException; 
	void update() throws SQLException;
	T find(String code) throws SQLException;
	List <T> findForName(String nome) throws SQLException;
	List <T> findAll() throws SQLException;
}
