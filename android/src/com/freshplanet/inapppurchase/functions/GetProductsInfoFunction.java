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

package com.freshplanet.inapppurchase.functions;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.android.vending.billing.IInAppBillingService;
import com.freshplanet.inapppurchase.Extension;
import org.json.JSONException;
import org.json.JSONObject;

public class GetProductsInfoFunction extends BaseFunction
{
	@Override
	public FREObject call(FREContext context, FREObject[] args)
	{
		super.call(context, args);
		
		List<String> skusName = getListOfStringFromFREArray((FREArray)args[0]);
		List<String> skusSubsName = getListOfStringFromFREArray((FREArray)args[1]);

		ArrayList<String> skuList = new ArrayList<String>(skusName);
		ArrayList<String> subsList = new ArrayList<String>(skusSubsName);

		final Bundle querySkus = new Bundle();
		querySkus.putStringArrayList("ITEM_ID_LIST", skuList);

		final IInAppBillingService mService = Extension.mService;
		final Activity appActivity = Extension.context.getActivity();
		final ServiceConnection mServiceConn;

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				Bundle skuDetails = null;
				int response = -1;

				try {

					skuDetails = mService.getSkuDetails(3, appActivity.getPackageName(), "inapp", querySkus);
					response = skuDetails.getInt("RESPONSE_CODE");
				}
				catch (RemoteException exception) {}

				if (response != 0) {

					// error
				}
				else {

					ArrayList<String> responseList = skuDetails.getStringArrayList("DETAILS_LIST");

					try {

						for (String thisResponse : responseList) {

							JSONObject productObject = new JSONObject(thisResponse);

						}
					}
					catch (JSONException exception) {}
				}
			}
		});

		thread.start();
		
		return null;
	}
}