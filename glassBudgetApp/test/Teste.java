import java.sql.SQLException;
import java.util.List;

import models.Cliente;
import models.TipoVidro;
import daos.ClienteDAO;
import daos.TipoVidroDAO;


public class Teste {
	public static void main(String[] args) throws SQLException {
		TipoVidroDAO dao = new TipoVidroDAO();
		TipoVidro t = new TipoVidro("nome", 1.0, 200, "not valid");
		TipoVidro t2 = new TipoVidro("nome2", 1.0, 150, "not valid");
		//dao.save(c1);
		//dao.save(c2);
		//c1.setNome("Rafael");
		//dao.update(c1);
		//dao.delete(c2);
		dao.delete(7);
		dao.delete(6);
		List <TipoVidro> l = dao.show();
		for (TipoVidro cliente : l) {
			System.out.println(cliente);
		}
	}
}
