package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
public class VendaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

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
		List<Livro> livros = livroDao.listaTodos();
		
		Random random = new Random(System.currentTimeMillis());
		for (Livro livro : livros) {
			Venda venda = new Venda(livro, random.nextInt(500));
			vendas.add(venda);
		}
		
		return  vendas;		
	}

}
