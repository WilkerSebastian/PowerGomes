/*
*
*   Desenvolvedores: Wilker Sebastian Afonso Pereira e Gabriel Taveira Granja
*
*   Turma: 229A
*
*   Atividade: Avaliação 1
*
*   Matéria: TLP2
*
*   GitHub: https://github.com/WilkerSebastian/PowerGomes
*
*/
package tlp2av1.com.PowerGomes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class PowerGomesApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(PowerGomesApplication.class, args);
	}

	@Override
	public void run(String...args){

	}

	@Bean
    public MessageSource messageSource() { 
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
