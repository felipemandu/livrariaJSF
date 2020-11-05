package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;

@Named
public class VendaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendaDao dao;

	public BarChartModel getChartModel() {
		BarChartModel chartModel = new BarChartModel();
		ChartSeries vendasSeries = new ChartSeries();
		
		List<Venda> vendas = getVendas();
		vendas.forEach(venda -> vendasSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade()));
		
		chartModel.addSeries(vendasSeries);
		
		return chartModel;
	}
	
	public List<Venda> getVendas() {
		List<Venda> vendas = dao.listaTodos();
		return  vendas;		
	}

}
