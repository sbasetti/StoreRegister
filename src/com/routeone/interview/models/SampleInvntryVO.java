package com.routeone.interview.models;

import java.math.BigDecimal;

public class SampleInvntryVO implements Comparable<SampleInvntryVO>
{
	private String componentName;
	private BigDecimal price = new BigDecimal("0.00");
	private String category;

	public SampleInvntryVO(String componentName, BigDecimal price, String category)
	{
		super();
		this.componentName = componentName;
		this.price = price;
		this.category = category;
	}

	public String getComponentName()
	{
		return componentName;
	}

	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	@Override
	public int compareTo(SampleInvntryVO o)
	{
		// TODO Auto-generated method stub

		if (this.getPrice().compareTo(o.getPrice()) > 0)
		{
			return -1;

		} else
			return 0;

	}

	@Override
	public String toString()
	{
		return "SampleInvntryVO [componentName=" + componentName + ", price=" + price + ", category=" + category + "]";
	}

}
