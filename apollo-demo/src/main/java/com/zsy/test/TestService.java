package com.zsy.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.zsy.bean.TestJavaConfigBean;
@Component
@Service
public class TestService {
	@Autowired
	TestJavaConfigBean javaConfigBean;
	
	@Value("${someKeyFromApollo:test1212}")
	private String test;
	static {
		Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
		config.addChangeListener(new ConfigChangeListener() {
		    @Override
		    public void onChange(ConfigChangeEvent changeEvent) {
		        System.out.println("Changes for namespace " + changeEvent.getNamespace());
		        for (String key : changeEvent.changedKeys()) {
		            ConfigChange change = changeEvent.getChange(key);
		            System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
		        }
		    }
		});
	}
	
	public String test(){
		/*Config config = ConfigService.getAppConfig();
		String someKey = "test";
		String someDefaultValue = "123121";
		String value = config.getProperty(someKey, someDefaultValue);
		System.err.println(value);*/
		//System.err.println(config.getPropertyNames());
		String s = javaConfigBean.toString();
		System.err.println(s);
		return s;
	}
	
}
