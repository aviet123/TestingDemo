package com.example.demo;


import com.example.demo.model.MathApp;
import com.example.demo.model.Portfolio;
import com.example.demo.model.Stock;
import com.example.demo.service.CalculatorService;
import com.example.demo.service.StockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTester {

    @Spy
    List<Stock> stocks = new ArrayList<>();

    @Mock
    StockService stockService;

    @InjectMocks
    Portfolio portfolio = new Portfolio();

    @Test
    public void testMarketValue(){
        Stock googleStock = new Stock(1L,"google",10);
        Stock microsoftStock = new Stock(2L,"microsoft",100);
        stocks.add(googleStock);
        stocks.add(microsoftStock);

        portfolio.setStocks(stocks);

        Mockito.when(stockService.getPrice(googleStock)).thenReturn(50.00);
        Mockito.when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        double marketValue = portfolio.getMarketValue();
        Assert.assertEquals(100500.0,marketValue,0.0);
    }

    @InjectMocks
    MathApp mathApp = new MathApp();

    @Mock
    CalculatorService calculatorService;

    @Test
    public void testAddMethod(){
        Mockito.when(calculatorService.add(10.0,20.0)).thenReturn(30.00);
        Mockito.when(calculatorService.subtract(20.0,10.0)).thenReturn(10.00);

        Assert.assertEquals(mathApp.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApp.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApp.add(10.0, 20.0),30.0,0);

        Assert.assertEquals(mathApp.subtract(20.0, 10.0),10.0,0);

        Mockito.verify(calculatorService).subtract(20.0,10.0);
        Mockito.verify(calculatorService, Mockito.times(3)).add(10.0,20.0);
        Mockito.verify(calculatorService,Mockito.never()).multiply(10.0,20.0);
    }

    @Test(expected = RuntimeException.class)
    public void testAdd(){
        Mockito.when(calculatorService.add(10.0,20.0))
                .thenThrow(new RuntimeException("add operation is not implemented"));
        Assert.assertEquals(mathApp.add(10.0,20.0),30.0,1.0);
    }

}
