package br.com.resource;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.controller.ControllerCliente;
import br.com.model.Cliente;
import br.com.model.Endereco;

/**
 * Classe de acesso do webservice
 */

@Path("/cliente")
public class ClienteService {

	/**
	 * @return ArrayList<cliente>
	 * @throws Exception
	 */

	@GET
	@Produces("application/json")
	@Path("/list")
	public String lista() {
		try {
			ArrayList<Cliente> lista = new ArrayList<>();
			ControllerCliente c = new ControllerCliente();
			lista = c.listar();
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
			Cliente clie = null;
			ControllerCliente c = new ControllerCliente();
			for (Cliente cliente : c.listar()) {
				if (cliente.getId() == index) {
					clie = cliente;
				}
			}
			Gson g = new Gson();
			return g.toJson(clie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}
	}

	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("nome") String nome, @FormParam("cpf") int cpf, @FormParam("cep") int cep,
			@FormParam("pais") String pais, @FormParam("estado") String estado, @FormParam("cidade") String cidade,
			@FormParam("bairro") String bairro, @FormParam("rua") String rua, @FormParam("numero") int numero,
			@FormParam("complemento") String complemento, @FormParam("telefone") int telefone,
			@FormParam("celular") int celular) {
		try {
			Endereco end = new Endereco(0, cep, pais, estado, cidade, bairro, rua, numero, complemento);
			ArrayList<String> telefones = new ArrayList<>();
			telefones.add("" + telefone);
			telefones.add("" + celular);
			Cliente clie = new Cliente();
			clie.setNome(nome);
			clie.setCpf(cpf);
			clie.setEndereco(end);
			clie.setTelefone(telefones);

			ControllerCliente c = new ControllerCliente();
			c.insert(clie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@POST
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("id") int id, @FormParam("nome") String nome, @FormParam("cep") int cep,
			@FormParam("pais") String pais, @FormParam("estado") String estado, @FormParam("cidade") String cidade,
			@FormParam("bairro") String bairro, @FormParam("rua") String rua, @FormParam("numero") int numero,
			@FormParam("complemento") String complemento, @FormParam("telefone") String telefone,
			@FormParam("celular") String celular) {
		try {
			Endereco end = new Endereco(0, cep, pais, estado, cidade, bairro, rua, numero, complemento);
			ArrayList<String> telefones = new ArrayList<>();
			telefones.add(telefone);
			telefones.add(celular);
			Cliente clie = new Cliente();
			clie.setId(id);
			clie.setNome(nome);
			clie.setEndereco(end);
			clie.setTelefone(telefones);

			ControllerCliente c = new ControllerCliente();
			c.update(clie);
			return "atualizado";
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
			ControllerCliente c = new ControllerCliente();
			c.delete(index);
			Gson g = new Gson();

			return g.toJson("deletado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERRO" + e.getMessage();
		}
	}
}
