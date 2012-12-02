package daos;

import java.sql.SQLException;
import java.util.List;
/**
 * Inteface que sugere a implementação do CRUD
 * 
 * */
public interface DAOModel <T>{
	
	void save() throws SQLException;
	void delete() throws SQLException; 
	void update() throws SQLException;
	T find() throws SQLException;
	List <T> findAll() throws SQLException;
}
