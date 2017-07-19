package br.com.resource;

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
	public String lista() throws Exception{		
		ArrayList<Servico> lista = new ArrayList<>();
		ControllerServico c = new ControllerServico();
		lista = c.listServico();
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	@POST
	@Produces("application/json")
	@Path("/pesquisar/")
	public String pesquisar(@FormParam("index") int index) throws Exception{		
		Servico servico = null; 
		ControllerServico c = new ControllerServico();
		for(Servico s: c.listServico()){
			if(s.getId() == index){
				servico = s;
			}
		}
		Gson g = new Gson();
		return g.toJson(servico);
	}
	
	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("nome") String nome,
						@FormParam("tipo") String tipo,
						@FormParam("valor") double valor
						) throws Exception{
 		
		Servico s = new Servico(0, nome, tipo, valor, 'A');
		
		ControllerServico c = new ControllerServico();
		c.insertServico(s);
	}
	
	@POST	
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("nome") String nome,
						@FormParam("tipo") String tipo,
						@FormParam("valor") double valor
						) throws Exception{

		Servico s = new Servico(0, nome, tipo, valor, 'A');

		ControllerServico c = new ControllerServico();
		c.updateServico(s);
		
		return "atualizado";
	}
	
	@POST
	@Produces("application/json")
	@Path("/delete/")
	public String delete(@FormParam("index") int index) throws Exception{		
		ControllerServico c = new ControllerServico();
		c.deleteServico(index);		
		Gson g = new Gson();
		return g.toJson("deletado");
	}
}
