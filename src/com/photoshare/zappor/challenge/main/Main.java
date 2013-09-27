package com.photoshare.zappor.challenge.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.photoshare.zappor.challenge.pricefacet.PriceBean;
import com.photoshare.zappor.challenge.pricefacet.PriceFacetRequest;
import com.photoshare.zappor.challenge.pricefacet.PriceFacetResponse;
import com.photoshare.zappor.challenge.pricefacet.PriceHelper;
import com.photoshare.zappor.challenge.pricefacet.PriceFacetRequest.PriceFacetRequestBuilder;
import com.photoshare.zappor.challenge.product.ProductBean;
import com.photoshare.zappor.challenge.product.ProductHelper;
import com.photoshare.zappor.challenge.product.SearchProductByPriceRequest;
import com.photoshare.zappor.challenge.product.SearchProductByPriceResponse;

public class Main {

	public static void main(String[] args) {
		PriceFacetRequest request = new PriceFacetRequestBuilder()
				.excludeResults(true).facetSortByName(true).filter(true)
				.filterOnSale(true).includeOnSale(true).onSale(true).term("")
				.build();
		PriceFacetResponse response = new PriceFacetResponse("");
		PriceHelper priceHelper = new PriceHelper();
		List<PriceBean> beans = priceHelper.getPriceFacets(request, response);

		Scanner scan = new Scanner(System.in);
		int number = 0;
		double dollar = 0;
		while(scan.hasNextLine()) {
			System.out.println("Please input the desired number of products:");
			number = scan.nextInt();
			System.out.println("Please input the desired dollar amount:");
			dollar = scan.nextDouble();
			Algorithm algorithm = new Algorithm();
			List<SelectedPrice> selectedPrices = new ArrayList<SelectedPrice>();
			computeClosestProductList(beans, algorithm, selectedPrices, number,
					dollar);
		}
		scan.close();
	}

	/**
	 * Calculate the closest value and print the chosen products. 
	 * 
	 * @param beans
	 * @param algorithm
	 * @param selectedPrices
	 * @param number
	 * @param dollar
	 */
	private static void computeClosestProductList(List<PriceBean> beans,
			Algorithm algorithm, List<SelectedPrice> selectedPrices,
			int number, double dollar) {
		double closestValue = algorithm.findClosestValue(number, dollar, beans,
				selectedPrices);

		List<ProductBean> satisfyProducts = new ArrayList<ProductBean>();

		for (SelectedPrice selectedPrice : selectedPrices) {
			SearchProductByPriceRequest productByPriceRequest = new SearchProductByPriceRequest.SearchProductByPriceRequestBuilder()
					.Price(selectedPrice.getPrice()).IncludeOnSale(true)
					.OnSale(true).Limit(selectedPrice.getCount()).build();
			SearchProductByPriceResponse productByPriceResponse = new SearchProductByPriceResponse(
					"");
			ProductHelper productHelper = new ProductHelper();
			List<ProductBean> products = productHelper.getProductByPrice(
					productByPriceRequest, productByPriceResponse);
			satisfyProducts.addAll(products);
		}
		
		Logger.getAnonymousLogger().log(Level.INFO,
				"closestValue:" + closestValue);

		for (ProductBean product : satisfyProducts) {
			Logger.getAnonymousLogger().log(Level.INFO, product.toString());
		}
	}
}
