package com.photoshare.zappor.challenge.pricefacet;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.photoshare.zappor.challenge.main.ZapporRestUtils;

public class PriceHelper {

	public List<PriceBean> getPriceFacets(PriceFacetRequest request,
			PriceFacetResponse response) {
		String resp = ZapporRestUtils.request(request.action());
		JSONObject obj = ZapporRestUtils.checkResponse(resp);
		List<PriceBean> beans = null;
		try {
			beans = response.parse(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beans;
	}
	
	public static void main(String[] args) {
		List<PriceBean> list = new ArrayList<PriceBean>();
		for (int i = 0; i < 45815; i++) {
			list.add(new PriceBean());
		}
		System.out.println(list.size());
	}
}
