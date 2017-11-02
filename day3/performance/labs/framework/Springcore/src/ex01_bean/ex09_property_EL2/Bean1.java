package ex01_bean.ex09_property_EL2;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;




public class Bean1 
{

	
	@Value("#{systemProperties}")
	private Properties systemProperties;
	
   @Value("#{systemProperties['favorite.languare']}")
   private String text;
   
   @Value("#{systemProperties['java.version']}")
   private String version;
   
public String getText() {
	return text;
}

public String getVersion() {
	return version;
}

}
