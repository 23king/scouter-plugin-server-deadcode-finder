
/*
 *  Copyright 2016 Scouter Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 *  
 *  @author Se-Wang Lee
 */
package scouter.plugin.server.deadcodefind;


import java.io.UnsupportedEncodingException;

//import org.apache.commons.lang.exception.ExceptionUtils;

import scouter.lang.AlertLevel;
import scouter.lang.TextTypes;
import scouter.lang.pack.AlertPack;
import scouter.lang.pack.ObjectPack;
import scouter.lang.pack.XLogPack;
import scouter.lang.pack.XLogProfilePack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.server.Configure;
import scouter.server.Logger;
import scouter.server.core.AgentManager;
import scouter.server.db.TextPermRD;

/**
 * Scouter server plugin to send alert via Slack
 * 
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 */
public class DeadCodeFinderPlugin {
	final Configure conf = Configure.getInstance();
	
	@ServerPlugin(PluginConstants.PLUGIN_SERVER_XLOG)
	public void process(XLogPack pack){
		Logger.println("XLOG :"+pack.toString());
		Logger.println("desc("+pack.desc+") :"+TextPermRD.getString(TextTypes.DESC, pack.desc));
		Logger.println("method :"+TextPermRD.getString(TextTypes.METHOD, 4096));
		
	}
	
	

	private void println(Object o){
		if(conf.getBoolean("ext_plugin_slack_debug", false)){
			System.out.println(o);
			Logger.println(o);			
		}
	}
}
