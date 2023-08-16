package com.example.kafkaproducergenerator;

import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@RequiredArgsConstructor
@RestController
public class KafkaProducerGeneratorApplication {

    @GetMapping("/api/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/api/test2")
    public String test2() {
        System.out.println("ad");
        return "Test2";
    }

    /**
     * <p> <h1>MAIN</h1>
     * Test method number 3
     * </p>
     * @return value
     */
    @GetMapping("/api/test3")
    public String test3() {
        System.out.println("ad");
        System.out.println("ad");
        System.out.println("ad");
        System.out.println("ad");
        System.out.println("ad");
        return "Test2";
    }

    private final KafkaTemplate<String, Person> kafkaTemplate;
    private final PersonService service;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerGeneratorApplication.class, args);
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void generatePersonToKafkaTopic() {
        Person person = generatePerson();
        System.out.println("SEND: " + person);
        kafkaTemplate.send("person", person);
    }

    private Person generatePerson() {
        return new EasyRandom().nextObject(Person.class);
    }
    
}
