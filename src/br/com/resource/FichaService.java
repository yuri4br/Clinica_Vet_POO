package br.com.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.controller.ControllerAnimal;
import br.com.controller.ControllerFicha;
import br.com.model.Animal;
import br.com.model.Cliente;
import br.com.model.Clinica;
import br.com.model.Ficha;
import br.com.model.Funcionario;

/**
 * Classe de acesso do webservice
 *
 */

@Path("/ficha")
public class FichaService {

	/**
	 * @return ArrayList<animal>
	 * @throws Exception
	 */
	
	@GET
	@Produces("application/json")
	@Path("/list/")
	public String list(){
		ArrayList<Ficha> lista = new ArrayList<>();
		try {
			ControllerFicha c = new ControllerFicha();
			lista = c.listFicha();
			Gson g = new Gson();
			return g.toJson(lista);
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
			Ficha ficha = null;
			ControllerFicha c = new ControllerFicha();

			for (Ficha f : c.listFicha()) {
				if (f.getId() == index) {
					ficha = f;
				}
			}

			Gson g = new Gson();
			return g.toJson(ficha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO - " + e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO - " + e.getMessage();
		}

	}

	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("id_cliente") int id_cliente, @FormParam("id_clinica") int id_clinica,
			@FormParam("id_funcionario") int id_funcionario, @FormParam("id_Animal") int id_animal,
			@FormParam("agendamento") String agendamento, @FormParam("diagnostico") String diagnostico,
			@FormParam("tipo") String tipo, @FormParam("situacao") String situacao) {

		try {
			Ficha f = new Ficha();

			Cliente cli = new Cliente();
			cli.setId(id_cliente);
			f.setCliente(cli);

			Clinica clin = new Clinica();
			clin.setId(id_clinica);
			f.setClinica(clin);

			Funcionario fun = new Funcionario();
			fun.setId(id_funcionario);
			f.setFuncionario(fun);

			Animal a = new Animal();
			a.setId(id_animal);
			f.setAnimal(a);

			f.setAgendamento(agendamento);
			f.setDiagnostico(diagnostico);
			f.setTipo(tipo);
			f.setSituacao(situacao);

			ControllerFicha c = new ControllerFicha();
			c.insertFicha(f);
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
	public String update(@FormParam("id") int id, @FormParam("nome") String nome, @FormParam("tipo") String tipo,
			@FormParam("raca") String raca, @FormParam("nascimento") String nascimento) {
		try {
			Animal a = new Animal(id, 0, 0, nome, tipo, raca, nascimento, 'A');
			ControllerAnimal c = new ControllerAnimal();
			c.updateAnimal(a);
			return "atualizado";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO " + e.getMessage();
		}

	}
}
