package de.tot.microservices.services.csv;

import com.opencsv.CSVReader;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Simple Test to read a CSV file and write it to the log
 */
@SpringBootApplication
@EnableDiscoveryClient
@PropertySource("classpath:db-config.properties")
public class CsvServer {

    protected Logger logger = Logger.getLogger(CsvServer.class.getName());

    public CsvServer() {
        try {
            ClassPathResource resource = new ClassPathResource("mytest.csv");
            CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(resource.getInputStream())), ';');
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
//                logger.info(nextLine[0] + nextLine[1] + "etc...");
                logger.info(nextLine[0]);
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "csv-server");

        ApplicationContext ctx = SpringApplication.run(CsvServer.class, args);

//        CsvServer server = new CsvServer();
/*
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
*/
    }
}
