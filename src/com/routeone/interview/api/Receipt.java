package com.routeone.interview.api;

import java.util.List;

public interface Receipt
{
	/** * @return Currency formatted total ($X,XXX.XX) of all items */
	public String getFormattedTotal();

	/** * @return List of all items in descending order by amount */
	public List<String> getOrderedItems();
}
