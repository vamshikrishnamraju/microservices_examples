package ex01_bean.ex06_factory;



public class ExampleStaticFactory
{

   public static Bean1 createBean(String val)
   {
      return new Bean1(val);
   }
   
   
}
