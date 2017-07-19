package br.com.resource;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.com.controller.ControllerFuncionario;
import br.com.model.Endereco;
import br.com.model.Funcionario;

/**
 * Classe de acesso do webservice
 *
 */

@Path("/funcionario")
public class FuncionarioService {
	
	/**
	 * @return ArrayList<cliente>
	 * @throws Exception 
	 */
	
	@GET	
	@Produces("application/json")
	@Path("/list")
	public String lista() throws Exception{		
		ArrayList<Funcionario> lista = new ArrayList<>();
		ControllerFuncionario c = new ControllerFuncionario();
		
		lista = c.listFuncionario();
		
		Gson g = new Gson();
		return g.toJson(lista);
	}
	
	@POST	
	@Produces("application/json")
	@Path("/pesquisar/")
	public String pesquisar(@FormParam("index") int index) throws Exception{		
		Funcionario funcionario = null; 
		ControllerFuncionario c = new ControllerFuncionario();
		for(Funcionario f: c.listFuncionario()){
			if(f.getId() == index){
				funcionario = f;
			}
		}
		Gson g = new Gson();
		return g.toJson(funcionario);
	}
	
	@POST
	@Produces("application/json")
	@Path("/inserir/")
	public void inserir(@FormParam("nome") String nome,
						@FormParam("cpf") int cpf,
						@FormParam("rg") int rg,
						@FormParam("cep") int cep,
						@FormParam("pais") String pais,
						@FormParam("estado") String estado,
						@FormParam("cidade") String cidade,
						@FormParam("bairro") String bairro,
						@FormParam("rua") String rua,
						@FormParam("numero") int numero,
						@FormParam("complemento") String complemento,
						@FormParam("telefone") int telefone,
						@FormParam("celular") int celular,
						@FormParam("cargo") int cargo,
						@FormParam("login") String login,
						@FormParam("senha") String senha,
						@FormParam("salaria") Double salario
						) throws Exception{
		Endereco end = new Endereco(0, cep, pais, estado, cidade, bairro, rua, numero, complemento);
		ArrayList<String> telefones  = new ArrayList<>();
		telefones.add(""+telefone);
		telefones.add(""+celular);
	
		Funcionario f = new Funcionario();
		f.setNome(nome);
		f.setCpf(cpf);
		f.setRg(rg);
		f.setEndereco(end);
		f.setTelefone(telefones);
		f.setCargo(cargo);
		f.setLogin(login);
		f.setSenha(senha);
		f.setSalario(salario);
		
		ControllerFuncionario c = new ControllerFuncionario();
		c.insertFuncionario(f);
		
		System.out.println("foi");
	}
	
	@POST
	@Produces("application/json")
	@Path("/update/")
	public String update(@FormParam("id") int id,
						@FormParam("nome") String nome,
						@FormParam("cpf") int cpf,
						@FormParam("rg") int rg,
						@FormParam("cep") int cep,
						@FormParam("pais") String pais,
						@FormParam("estado") String estado,
						@FormParam("cidade") String cidade,
						@FormParam("bairro") String bairro,
						@FormParam("rua") String rua,
						@FormParam("numero") int numero,
						@FormParam("complemento") String complemento,
						@FormParam("telefone") int telefone,
						@FormParam("celular") int celular,
						@FormParam("cargo") int cargo,
						@FormParam("login") String login,
						@FormParam("senha") String senha,
						@FormParam("salaria") Double salario
						) throws Exception{
		Endereco end = new Endereco(0, cep, pais, estado, cidade, bairro, rua, numero, complemento);
		ArrayList<String> telefones  = new ArrayList<>();
		telefones.add(""+telefone);
		telefones.add(""+celular);
	
		Funcionario f = new Funcionario();
		f.setId(id);
		f.setNome(nome);
		f.setCpf(cpf);
		f.setRg(rg);
		f.setEndereco(end);
		f.setTelefone(telefones);
		f.setCargo(cargo);
		f.setLogin(login);
		f.setSenha(senha);
		f.setSalario(salario);
		
		ControllerFuncionario c = new ControllerFuncionario();
		c.updateFuncionario(f);
		
		return "atualizado";
	}
	
	@POST
	@Produces("application/json")
	@Path("/delete/")
	public String delete(@FormParam("index") int index) throws Exception{		
		ControllerFuncionario c = new ControllerFuncionario();
		c.deleteFuncionario(index);		
		Gson g = new Gson();
		return g.toJson("deletado");
	}
	

}
