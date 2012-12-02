import java.sql.SQLException;
import java.util.List;

import models.Cliente;
import models.Pedido;
import models.Produto;
import models.TipoVidro;
import daos.ClienteDAO;
import daos.PedidoDAO;
import daos.ProdutoDAO;
import daos.TipoVidroDAO;


public class Teste {
	public static void main(String[] args) throws SQLException {
		Produto p = new Produto(2, "Cas", 1.5, 1.2, 3);
	}
}
