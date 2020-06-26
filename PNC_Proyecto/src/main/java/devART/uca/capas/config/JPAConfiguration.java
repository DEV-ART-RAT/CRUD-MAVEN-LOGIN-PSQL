package devART.uca.capas.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "devART.uca.capas.repositories")
public class JPAConfiguration {
	//	Aqui es la correlacion para establecer correlacion entre la base y nuestro sistema 	orientado a objetos
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		//	manejamos los datos
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		//	Obtenemos la ubicacion de los datos
		em.setDataSource(dataSource());
		// paquetes donde lo recibiran
		em.setPersistenceUnitName("capas");
		em.setPackagesToScan("devART.uca.capas.domain");
		// Adaptador
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;	
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/PNC_PROYECTO");
		dataSource.setUsername("PNC_proyecto");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	
	Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return properties;
	}

}