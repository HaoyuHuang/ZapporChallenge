package com.photoshare.zappor.challenge.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.photoshare.zappor.challenge.pricefacet.PriceBean;

public class Algorithm {

	public double findClosestValue(int nums, double sumBudgets,
			List<PriceBean> beans, List<SelectedPrice> result) {
		int numberSize = 0;
		for (int i = 0; i < beans.size(); i++) {
			if (beans.get(i).getName() > sumBudgets) {
				numberSize = i;
				break;
			}
		}
		int[] numbers = new int[numberSize];
		double[] prices = new double[numberSize];
		double closetValue = 0;
		for (int i = 0; i < numberSize; i++) {
			numbers[i] = beans.get(i).getCount();
			prices[i] = beans.get(i).getName();
		}
		closetValue = multiKnapsack(sumBudgets, nums, numbers, prices, result);
		return closetValue;
	}

	/**
	 * f[i][v][item] = max {f[i - 1][v - k * price[i - 1]][item] + k * price[i -
	 * 1]} | 0<=k<=num[i] f[i][v][item] represents the maximum value from the
	 * first i price, total budget of v and the number of items can be choosed.
	 * 
	 * @param totalBudget
	 *            The total budget from user inputs
	 * @param items
	 *            The number of items from user inputs
	 * @param num
	 *            The number of products with the same price
	 * @param price
	 *            The price array
	 */
	public double multiKnapsack(double totalBudget, int items, int num[],
			double price[], List<SelectedPrice> result) {
		double f[][][] = new double[price.length + 1][(int) (totalBudget + 1)][items];
		int count[][] = new int[price.length + 1][(int) (totalBudget + 1)];
		double max = 0;
		int recordUsed = 0;
		double closestValue = 0;

		for (int i = 1; i < price.length + 1; i++) {
			for (int v = 0; v < totalBudget + 1; v++) {
				for (int item = 0; item < items; item++) {
					for (int k = 0; k <= num[i - 1]; k++) {
						if ((int) (v - k * price[i - 1]) < 0) {
							continue;
						}
						if (count[i - 1][(int) (v - k * price[i - 1])] + k > items) {
							continue;
						}
						double temp = f[i - 1][(int) (v - k * price[i - 1])][item]
								+ k * price[i - 1];
						if (temp > max) {
							max = temp;
							recordUsed = k;
						}
					}
					f[i][v][item] = max;
					count[i][v] = count[i - 1][(int) (v - recordUsed
							* price[i - 1])]
							+ recordUsed;
					max = 0;
					recordUsed = 0;
				}
			}
		}

		// find the closest value and its index in the table;
		double min = Double.MAX_VALUE;
		double temp = 0;
		int record = 0;
		for (int j = 0; j < totalBudget; j++) {
			temp = Math.abs(totalBudget
					- f[price.length][(int) (totalBudget - j)][items - 1]);
			if (temp < min) {
				min = temp;
				record = j;
			}
		}

		int i = price.length;
		int index = i;
		int k = (int) (totalBudget - record);
		int item = items;
		closestValue = f[i][k][items - 1];

		// find the one in the matrix nearest to the closest value
		while (f[i - 1][k][items - 1] == closestValue) {
			i--;
		}

		// find the used product
		while (index != 0) {
			for (int j = 1; j <= num[i - 1]; j++) {
				if (k - j * price[i - 1] < 0) {
					continue;
				}
				if (count[i - 1][((int) (k - j * price[i - 1]))] + j == count[i][k]) {
					Logger.getAnonymousLogger().log(Level.INFO, "Select " + j + " Products with cost "+ price[i - 1]);
					
					k = ((int) (k - j * price[i - 1]));
					i--;
					item -= j;
					result.add(new SelectedPrice(price[i - 1], j));
					break;
				}
			}
			if (count[i][k] == item) {
				Logger.getAnonymousLogger().log(Level.INFO, "Select " + item + " Products with cost "+ price[i - 1]);
				result.add(new SelectedPrice(price[i - 1], item));
				break;
			}
			index--;
		}
		return closestValue;
	}

	public double max(double a, double b) {
		return a > b ? a : b;
	}

	public static void main(String[] args) {
		Algorithm al = new Algorithm();
		List<PriceBean> list = new ArrayList<PriceBean>();
		Random r = new Random();
		for (int i = 0; i < 3000; i++) {
			list.add(new PriceBean(i + 6.99, r.nextInt(10) + 1));
		}
		double s = al.findClosestValue(3, 300, list, null);
		System.out.println(s);
	}

}
