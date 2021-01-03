package compeng.arved;

import compeng.arved.domain.*;
import compeng.arved.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class ArvedApplication implements CommandLineRunner {

    private final StaffRepository staffRepository;

    @Autowired
    public ArvedApplication(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ArvedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        staffRepository.deleteAll();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = dateFormat.format(date);

        staffRepository.save(new Staff(null, "refik", "samet", "xxxxx@gmail.com", "password", null, "computer engineering", new StaffInformation(3, 4, 5, 6, "computer science"),
                Arrays.asList(new Article("makale", "sci", "augmented reality in healthcare", "refik samet", stringDate, "dergi", "konferans", 2, 20, 32, "10.30803/adusobed.438062")),
                Arrays.asList(new Project("proje adi", "refik samet", "amaç", "devam ediyor", true, false, "tübitak 1001", "sosyal", 100.000, "tl", true, false, false, 15))
                ));


    }
}
