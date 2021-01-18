package compeng.arved;

import compeng.arved.domain.*;
import compeng.arved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class ArvedApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public ArvedApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ArvedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* userRepository.deleteAll();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = dateFormat.format(date);*/


        /*staffRepository.save(new User("1", "refik", "samet", "xxxxx@gmail.com", "password", null, "computer engineering", new StaffInformation(null, 3, 4, 5, 6, "computer science"),
                Arrays.asList(new Article(null,"makale", "sci", "augmented reality in healthcare", "refik samet", stringDate, "dergi", "konferans", 2, 20, 32, "10.30803/adusobed.438062", null)),
                Arrays.asList(new Project(null,"proje adi", "refik samet", "amaç", "devam ediyor", true, false, "tübitak 1001", "sosyal", 100.000, "tl", true, false, false, 15))
                ));*/


        /*Optional<User> optionalStaff = userRepository.findById("1");
        if (optionalStaff.isPresent()) {
            User user = optionalStaff.get();
            System.out.println(user.getArticles());
        }*/
    }


}
