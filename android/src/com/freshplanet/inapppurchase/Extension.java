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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.android.vending.billing.IInAppBillingService;

public class Extension implements FREExtension
{
	private static String TAG = "AirInAppPurchase";
	
	public static ExtensionContext context;
	public static IInAppBillingService mService;
	public static ServiceConnection mServiceConn;

	public void initialize()
	{
		mServiceConn = new ServiceConnection() {
			@Override
			public void onServiceDisconnected(ComponentName name) {
				mService = null;
			}

			@Override
			public void onServiceConnected(ComponentName name,
										   IBinder service) {
				mService = IInAppBillingService.Stub.asInterface(service);
			}
		};

		Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		serviceIntent.setPackage("com.android.vending");
		context.getActivity().bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
	}

	public FREContext createContext(String extId)
	{
		return context = new ExtensionContext();
	}

	public void dispose()
	{
		context = null;
	}

	public static void log(String message)
	{
		Log.d(TAG, message);
	}
}
