package ex01_bean.ex10_resources;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;


public class Bean1
{

   /**
    * Property Setter to allow Resource to be passed into this Bean
    * 
    */
   public void setResource(Resource resource)
      throws IOException
   {
      // Use the Resource to construct some Properties for later use
      // Note: The Resource may have been found using the File System, Class Path
      //       or any means.
      m_resourceProperties = new Properties();
      m_resourceProperties.load(resource.getInputStream());
   }

   public String getMessage()
   {
      return m_resourceProperties.getProperty("helloworld");
   }
   
   private Properties m_resourceProperties;
   
}
