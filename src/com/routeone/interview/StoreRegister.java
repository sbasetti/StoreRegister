package com.routeone.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.routeone.interview.api.Receipt;
import com.routeone.interview.models.SampleInvntryVO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StoreRegister
{

	private final DecimalFormat FORMATTER = new DecimalFormat("$#,###.00");
	private List<SampleInvntryVO> invntryAL = new ArrayList<SampleInvntryVO>(); // Get Complete inventory
	private List<SampleInvntryVO> subsetInvntryAL = new ArrayList<SampleInvntryVO>(); // Order lineItems - part of
																																										// inventory
	public static Receipt receiptImpl;  // as Receipt is implemented inside a method, not using static raises compilation
					    // error.
	private BigDecimal totalCost = new BigDecimal("0.00");
	

	public static void main(String[] args)
	{
		StoreRegister storeRegstr = new StoreRegister();
		String[] a1rgs = { "PC1033", "LCD", "GenericMotherboard", "Mouse", "GenericMotherboardV2", "LCD",
				"GenericProcessor" };

		List<String> inputStrAL = Arrays.asList(a1rgs);

		File inventoryFile = new File("sample-inventory.csv");
		storeRegstr.loadInventory(inventoryFile);
		receiptImpl = storeRegstr.checkoutOrder(inputStrAL);

		System.out.println(receiptImpl.getFormattedTotal());
		System.out.println(receiptImpl.getOrderedItems());

	}

	
	public void loadInventory(File inventoryFile)
	{

		try
		{

			BufferedReader bReader = new BufferedReader(new FileReader(inventoryFile));
			String record = "";
			while ((record = bReader.readLine()) != null)
			{
				String[] column = record.split(",");

				if (column != null && !column.toString().isEmpty())
					invntryAL.add(new SampleInvntryVO(column[0], new BigDecimal(column[1]), column[2]));
			}
			bReader.close();

		} catch (FileNotFoundException f)
		{
			f.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public Receipt checkoutOrder(List<String> items)
	{
		if (items == null)
		{
			System.out.println("No Check out Orders entered, No Output generated");
			System.exit(0);
		}
		for (String item : items)
		{
			for (SampleInvntryVO invntryObj : invntryAL)
			{
				if (invntryObj != null && invntryObj.getComponentName().equalsIgnoreCase(item))
				{
					subsetInvntryAL.add(invntryObj);
					totalCost = totalCost.add(invntryObj.getPrice());
					break;
				}
			}
		}

		receiptImpl = new Receipt()
		{
			public String getFormattedTotal()
			{
				return FORMATTER.format(totalCost);
			}

			public List<String> getOrderedItems()
			{

				Collections.sort(subsetInvntryAL);

				List<String> ordrdItemDescAL = new ArrayList<String>();
				for (SampleInvntryVO invntryObj : subsetInvntryAL)
				{
					ordrdItemDescAL.add(invntryObj.getComponentName());
				}
				return ordrdItemDescAL;
			}
		};
		return receiptImpl;
	}
}
