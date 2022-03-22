package server.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import server.database.ActivitiesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import commons.Activity;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivitiesRepository repository;

    public ActivityController(ActivitiesRepository repository){
        this.repository = repository;
    }

    public ActivityController(){

    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Activity> getAll()
    {
        return repository.findAll();
    }

    @GetMapping("/getActivityByWh")
    @ResponseBody
    public Activity getActivityByWh(@RequestParam Long energyConsumption) {
        List<Activity> act = repository.findByEnergyConsumptionDesc(energyConsumption);
        if(act.isEmpty())
            return null;
        return act.get(0);
    }

    @GetMapping("/getRandomActivity")
    @ResponseBody
    public Activity getRandomActivity() {
        List<Activity> allAct = repository.findAll();
        Random ran = new Random();
        if(allAct.isEmpty())
            return null;
        return allAct.get(ran.nextInt(allAct.size()));
    }

    @GetMapping("/getActivityByID")
    @ResponseBody
    public Optional<Activity> getActivityByID(Long id) {
        return repository.findById(id);
    }
}
