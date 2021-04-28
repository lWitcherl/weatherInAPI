package com.dut.education.configurations;

import com.dut.education.communications.WeatherCommunicatorImp;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com.dut.education")
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringConfig {

    @Bean(name = "setting")
    public Properties settingProperties() throws IOException {
        Properties properties = new Properties();
        String part = WeatherCommunicatorImp.class.getClassLoader().getResource("setting.properties").getPath();
        properties.load(new FileInputStream(part));
        return properties ;
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public DataSource dataSource() throws IOException {
        Properties properties = settingProperties();
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl(properties.getProperty("dataSource.URL"));
            dataSource.setUser(properties.getProperty("dataSource.User"));
            dataSource.setPassword(properties.getProperty("dataSource.Password"));
        }catch (PropertyVetoException e){
            e.getMessage();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.dut.education.entitys");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.ddl-auto","update");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager () throws IOException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
