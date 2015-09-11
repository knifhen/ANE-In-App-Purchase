package com.freshplanet.inapppurchase.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;

public class InitFunction extends BaseFunction
{
	@Override
	public FREObject call(FREContext context, FREObject[] args)
	{
		super.call(context, args);
		
		String key = getStringFromFREObject(args[0]);
		Boolean debug = getBooleanFromFREObject(args[1]);

		return null;	
	}
}