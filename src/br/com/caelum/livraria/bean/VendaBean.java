package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ApplicationScoped
public class VendaBean {
	
	public BarChartModel getChartModel() {
		BarChartModel chartModel = new BarChartModel();
		ChartSeries vendasSeries = new ChartSeries();
		
		List<Venda> vendas = getVendas();
		vendas.forEach(venda -> vendasSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade()));
		
		chartModel.addSeries(vendasSeries);
		
		return chartModel;
	}
	
	public List<Venda> getVendas() {
		List<Venda> vendas = new ArrayList<>();
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		
		Random random = new Random(System.currentTimeMillis());
		for (Livro livro : livros) {
			Venda venda = new Venda(livro, random.nextInt(500));
			vendas.add(venda);
		}
		
		return  vendas;		
	}

}
