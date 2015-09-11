//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright 2012 Freshplanet (http://freshplanet.com | opensource@freshplanet.com)
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//    http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//  
//////////////////////////////////////////////////////////////////////////////////////

package com.freshplanet.inapppurchase;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.freshplanet.inapppurchase.functions.GetProductsInfoFunction;
import com.freshplanet.inapppurchase.functions.InitFunction;
import com.freshplanet.inapppurchase.functions.MakePurchaseFunction;
import com.freshplanet.inapppurchase.functions.MakeSubscriptionFunction;
import com.freshplanet.inapppurchase.functions.RemovePurchaseFromQueuePurchase;
import com.freshplanet.inapppurchase.functions.RestoreTransactionFunction;

public class ExtensionContext extends FREContext
{
	public ExtensionContext() {}
	
	@Override
	public void dispose()
	{
		Extension.context = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions()
	{
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		
		functionMap.put("initLib", new InitFunction());
		functionMap.put("getProductsInfo", new GetProductsInfoFunction());
		functionMap.put("makePurchase", new MakePurchaseFunction());
		functionMap.put("restoreTransaction", new RestoreTransactionFunction());
		functionMap.put("removePurchaseFromQueue", new RemovePurchaseFromQueuePurchase());
		functionMap.put("makeSubscription", new MakeSubscriptionFunction());
		
		return functionMap;	
	}
}