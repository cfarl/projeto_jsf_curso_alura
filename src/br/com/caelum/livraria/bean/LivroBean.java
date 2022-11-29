package br.com.caelum.livraria.bean;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	private Integer livroId ;
	
	private Integer autorId; // Guarda o id do lautor selecionado na combobox

	private List<Livro> livros;

	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<String> getGeneros() {
	    return generos;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	//------------------------------------------------------
	/** Recupera todos os livros cadastrados */
	//------------------------------------------------------
	public List<Livro> getLivros() {
		if(this.livros == null) {
			this.livros = new DAO<Livro>(Livro.class).listaTodos();
		}
		return this.livros ;		
	}

	public List<Autor> getAutoresLivro() {
		return this.livro.getAutores();
	}

	// ----------------------------------------------------
	/** Validador personalizado */
	// ----------------------------------------------------
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
		}
	}

	// ----------------------------------------------------
	/** Adiciona um autor no livro */
	// ----------------------------------------------------
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
	}

	// ----------------------------------------------------
	/** Cadastra o livro */
	// ----------------------------------------------------
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
		}

		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livro.getId() == null) {
			dao.adiciona(this.livro);
		} else {
			dao.atualiza(this.livro);
		}
		this.livros = dao.listaTodos();

		this.livro = new Livro();
	}
	
	// ----------------------------------------------------
	/** Carrega o livro selecionado para alteracao */
	// ----------------------------------------------------
	public void carregar(Livro livroSelecionado) {
		System.out.println("Carregando o livro " + livroSelecionado.getTitulo() );
		this.livro = livroSelecionado ;
	}	

	// ----------------------------------------------------
	/** Remove o livro selecionado */
	// ----------------------------------------------------
	public void remover(Livro livroSelecionado) {
		System.out.println("Removendo livro " + livroSelecionado.getTitulo() );
		new DAO<Livro>(Livro.class).remove(livroSelecionado);
	}	
	
	// ----------------------------------------------------
	/** Remove o autor do livro selecionado */
	// ----------------------------------------------------
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor) ;
	}	
	
	public void carregarLivroPelaId() {
	    this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}	

}
