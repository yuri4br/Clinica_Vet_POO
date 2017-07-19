package br.com.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import br.com.controller.ControllerCargo;
import br.com.exception.NaoEncontradoException;
import br.com.model.Cargo;

/**
 * Classe de acesso do webservice
 *
 */

@Path("/cargo")
public class CargoService {
	
	/**
	 * @return ArrayList<animal>
	 * @throws Exception 
	 */
	
	@GET	
	@Produces("application/json")
	@Path("/list")
	public String lista() throws Exception{		
		ArrayList<Cargo> lista = new ArrayList<>();
		ControllerCargo c = new ControllerCargo();
		lista = c.listCargo();
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	@POST
	@Produces("application/json")
	@Path("/pesquisar/")
	public String pesquisar(@FormParam("index") int index) throws Exception{
		System.out.println("index: " +index);
		Cargo cargo = null; 
		ControllerCargo c = new ControllerCargo();
		for(Cargo car: c.listCargo()){
			if(car.getId() == index){
				cargo = car;
			}
		}
		Gson g = new Gson();
		return g.toJson(cargo);
	}
	
	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public String inserir(@FormParam("nome") String nome) throws Exception{
		String resposta = "cadastrado com sucesso";
		Cargo cargo = new Cargo(0, nome, 'A');
		
		ControllerCargo c = new ControllerCargo();
		c.insertCargo(cargo);
		
		return resposta;
	}
	
	@POST
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("id") int id,
						@FormParam("nome") String nome
						) throws Exception{
		Cargo cargo = new Cargo(id,nome,'A');
		
		ControllerCargo c = new ControllerCargo();
		c.updateAnimal(cargo);
		
		return "atualizado";
	}
	
	@POST
	@Produces("application/json")
	@Path("/delete/")
	public String delete(@FormParam("index") int index){		
		try {
			ControllerCargo c = new ControllerCargo();
			c.deleteCargo(index);
			Gson g = new Gson();
			return g.toJson("deletado");
		} catch (NaoEncontradoException e) {
			System.out.println(e.getStackTrace());
			Gson g = new Gson();
			return g.toJson("Cargo não encontrado");
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
			Gson g = new Gson();
			return g.toJson("Erro inesperado entrar em cotato com o suporte");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			Gson g = new Gson();
			return g.toJson("Erro inesperado entrar em cotato com o suporte");
		}		

	}
}
