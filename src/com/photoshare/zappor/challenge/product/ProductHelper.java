package com.photoshare.zappor.challenge.product;

import java.util.List;

import org.json.JSONObject;

import com.photoshare.zappor.challenge.main.ZapporRestUtils;

public class ProductHelper {
	
	public List<ProductBean> getProductByPrice(SearchProductByPriceRequest request,
			SearchProductByPriceResponse response) {
		String resp = ZapporRestUtils.request(request.action());
		JSONObject obj = ZapporRestUtils.checkResponse(resp);
		List<ProductBean> beans = null;
		try {
			beans = response.parse(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beans;
	}
}
