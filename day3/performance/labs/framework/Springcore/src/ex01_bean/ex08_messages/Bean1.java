package ex01_bean.ex08_messages;

import org.springframework.context.MessageSource;


public class Bean1
{

   public void setString(String val)
   {
      m_strVal = val;
   }
   
   public String getMessage()
   {
      // Make use of the message source to retrieve the localised message
      return m_messages.getMessage("helloworld", new Object[] {m_strVal}, null);
   }

   /**
    * Property Setter for allowing the Message Source to passed in
    */
   public void setMessageSource(MessageSource messages)
   {
      m_messages = messages;
   }
   
   
   private MessageSource m_messages;
   private String m_strVal;
}
