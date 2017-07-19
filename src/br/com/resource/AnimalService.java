package br.com.resource;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.controller.ControllerAnimal;
import br.com.model.Animal;

/**
 * Classe de acesso do webservice 
 *
 */

@Path("/animal")
public class AnimalService {
	
	/**
	 * @return ArrayList<animal>
	 * @throws Exception 
	 */
	
	@GET	
	@Produces("application/json")
	@Path("/list")
	public String lista() throws Exception{		
		ArrayList<Animal> lista = new ArrayList<>();
		ControllerAnimal c = new ControllerAnimal();
		lista = c.listAnimal();
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	@POST
	@Produces("application/json")
	@Path("/pesquisar/")
	public String pesquisar(@FormParam("index") int index) throws Exception{		
		Animal animal = null; 
		ControllerAnimal c = new ControllerAnimal();
		for(Animal a: c.listAnimal()){
			if(a.getId() == index){
				animal = a;
			}
		}
		Gson g = new Gson();
		return g.toJson(animal);
	}
	
	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("id_cliente") int id_cliente,
						@FormParam("id_clinica") int id_clinica,
						@FormParam("nome") String nome,
						@FormParam("tipo") String tipo,
						@FormParam("raca") String raca,
						@FormParam("nascimento") String nascimento
						) throws Exception{
		Animal a = new Animal(id_cliente, id_clinica, nome, tipo, raca, nascimento);
		
		ControllerAnimal c = new ControllerAnimal();
		c.insertAnimal(a);
		
		System.out.println("foi");
	}
	
	@POST	
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("id") int id,
						@FormParam("nome") String nome,
						@FormParam("tipo") String tipo,
						@FormParam("raca") String raca,
						@FormParam("nascimento") String nascimento
						) throws Exception{
		Animal a = new Animal(id, 0, 0, nome, tipo, raca, nascimento, 'A');
		
		ControllerAnimal c = new ControllerAnimal();
		c.updateAnimal(a);
		
		return "atualizado";
	}
	
	@POST	
	@Produces("application/json")
	@Path("/delete/")
	public String delete(@FormParam("index") int index) throws Exception{		
		ControllerAnimal c = new ControllerAnimal();
		c.deleteAnimal(index);		
		Gson g = new Gson();
		return g.toJson("deletado");
	}
}
