package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	private Integer autorId;

	public Integer getAutorId() {
	    return autorId;
	}

	public void setAutorId(Integer autorId) {
	    this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
	    this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		return "autor?faces-redirect=true" ; // Volta para a página livro.xhtml
	}
	
	//------------------------------------------------------
	/** Recupera todos os autores cadastrados */
	//------------------------------------------------------
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}	
	
	// ----------------------------------------------------
	/** Remove o autor selecionado */
	// ----------------------------------------------------
	public void remover(Autor autorSelecionado) {
		System.out.println("Removendo autor " + autorSelecionado.getNome() );
		new DAO<Autor>(Autor.class).remove(autorSelecionado);
	}
	
	// ----------------------------------------------------
	/** Carrega o autor selecionado para alteracao */
	// ----------------------------------------------------
	public void carregar(Autor autorSelecionado) {
		System.out.println("Carregando o autor " + autorSelecionado.getNome() );
		this.autor = autorSelecionado ;
	}		
	
	
}
