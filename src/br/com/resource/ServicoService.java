package br.com.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.controller.ControllerServico;
import br.com.model.Servico;

/**
 * Classe de acesso do webservice
 *
 */

@Path("/servico")
public class ServicoService {

	/**
	 * @return ArrayList<animal>
	 * @throws Exception
	 */

	@GET
	@Produces("application/json")
	@Path("/list")
	public String lista() {
		try {
			ArrayList<Servico> lista = new ArrayList<>();
			ControllerServico c = new ControllerServico();
			lista = c.listServico();

			Gson g = new Gson();
			return g.toJson(lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}

	}

	@POST
	@Produces("application/json")
	@Path("/pesquisar/")
	public String pesquisar(@FormParam("index") int index) {

		try {
			Servico servico = null;
			ControllerServico c = new ControllerServico();
			for (Servico s : c.listServico()) {
				if (s.getId() == index) {
					servico = s;
				}
			}

			Gson g = new Gson();
			return g.toJson(servico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}

	}

	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("nome") String nome, @FormParam("tipo") String tipo,
			@FormParam("valor") double valor) {

		try {

			Servico s = new Servico(0, nome, tipo, valor, 'A');

			ControllerServico c = new ControllerServico();
			c.insertServico(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@POST
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("nome") String nome, @FormParam("tipo") String tipo,
			@FormParam("valor") double valor) {

		try {
			Servico s = new Servico(0, nome, tipo, valor, 'A');

			ControllerServico c = new ControllerServico();
			c.updateServico(s);
			return "atualizado";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}

	}

	@POST
	@Produces("application/json")
	@Path("/delete/")
	public String delete(@FormParam("index") int index) {

		try {
			ControllerServico c = new ControllerServico();
			c.deleteServico(index);
			Gson g = new Gson();
			return g.toJson("deletado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}

	}
}
