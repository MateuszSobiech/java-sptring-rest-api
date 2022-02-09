package pl.sobiech.javasptringrestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sobiech.javasptringrestapi.DogsService;
import pl.sobiech.javasptringrestapi.Entity.Dog;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogsController {

    private DogsService dogsService;

    @Autowired
    public DogsController(DogsService dogsService) {
        this.dogsService = dogsService;
    }

    @GetMapping()
    public List<Dog> getDogs() {
        return dogsService.getDogs();
    }

    @GetMapping("/{id}")
    public Dog getDog(@PathVariable int id) {
        return dogsService.getDog(id);
    }

    @PostMapping()
    public List<Dog> addDog(@RequestBody Dog dto) {
        int random = (int) (Math.random() * 1000 + 1);
        Dog dog = new Dog(random, dto.getName(), dto.getRace());
        dogsService.addDog(dog);
        return dogsService.getDogs();
    }

    @DeleteMapping()
    public void deleteDog(@RequestParam int id) {
        dogsService.deleteDog(id);
    }
}
