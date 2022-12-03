package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {
	
	//-------------------------------------------------
	/** Recupera os dados do gráfico */
	//-------------------------------------------------
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel() ;
		model.setAnimate(true);
		
		// Define os dados de uma serie
		ChartSeries vendaSerie = new ChartSeries() ;
		vendaSerie.setLabel("Vendas 2016") ;
		
		List<Venda> vendas = getVendas() ;		
		for(Venda venda: vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		model.addSeries(vendaSerie);
		
		// Define os dados de outra serie
		ChartSeries vendaSerie2015 = new ChartSeries() ;
		vendaSerie.setLabel("Vendas 2015") ;
		
		vendas = getVendas() ;		
		for(Venda venda: vendas) {
			vendaSerie2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
				
		model.addSeries(vendaSerie2015);
		
		
		return model ;
	}
	
	//-----------------------------------------------------------
	/** Recupera as vendas que serão exibidas no gráfico */
	//-----------------------------------------------------------
	public List<Venda> getVendas() {
		List<Venda> vendas = new ArrayList<Venda>() ;
		
		// Recupera os livros cadastrados
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		
		// Gera uma venda para cada livro, usando uma quantidade aleatória entre 0 e 500
		Random random = new Random(System.currentTimeMillis());
		for(Livro livro: livros) {
			Integer quantidade = random.nextInt(500) ;
			vendas.add(new Venda(livro, quantidade)) ;
		}
		
		return vendas ;
	}
}
