package ex01_bean.ex09_property_EL2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Spring3HelloWorldConfig {

	  public @Bean(name="bean1") Bean1 spring3HelloWorld() {
	    return new Bean1();
	  }

}
