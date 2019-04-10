package red.hound.aliasshareconsumer;

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.admin.DeviceAdminReceiver;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.util.Log;

import static android.security.KeyChain.EXTRA_CERTIFICATE;
import static android.security.KeyChain.EXTRA_KEY_ACCESSIBLE;
import static android.security.KeyChain.EXTRA_KEY_ALIAS;
import static android.security.KeyChain.EXTRA_NAME;

/**
 * Handles events related to device owner.
 */
public class PurebredBroadcastReceiver extends BroadcastReceiver
{
    public static final String TAG = "AliasShareConsumer";
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        String log = sb.toString();
        Log.d(TAG, log);
        MainActivity.fetchAliases(context, null);
    }
}
