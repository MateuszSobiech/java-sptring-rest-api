package pl.sobiech.javasptringrestapi;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.sobiech.javasptringrestapi.Entity.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogsService {

    List<Dog> dogsList = new ArrayList<Dog>();

    @EventListener(ApplicationReadyEvent.class)
    public void createListAtStartup() {
        Dog dog1 = new Dog(1, "Bystra", "Alaskan Malamute");
        Dog dog2 = new Dog(2, "Tobi", "Shin Tzu");

        dogsList.add(dog1);
        dogsList.add(dog2);
    }

    public List<Dog> addDog(Dog dog) {
        dogsList.add(dog);
        return dogsList;
    }

    public List<Dog> getDogs() {
        return dogsList;
    }

    public Dog getDog(int id) {
        return dogsList
                .stream()
                .filter( dog -> dog.getId() == id)
                .findFirst().get();
    }

    public void deleteDog(int id) {
        dogsList = dogsList
                .stream()
                .filter(dog -> id != dog.getId())
                .collect(Collectors.toList());
    }
}
