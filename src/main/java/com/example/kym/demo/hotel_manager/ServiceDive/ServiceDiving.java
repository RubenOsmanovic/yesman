package com.example.kym.demo.hotel_manager.ServiceDive;

import com.example.kym.demo.hotel_manager.*;
import com.example.kym.demo.hotel_manager.dto.DiveProjection;
import com.example.kym.demo.hotel_manager.dto.EquipmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})

@Transactional
@Service
public class ServiceDiving {

    private final DiveRep diveRep;

    private final EquipmentRep equipmentRep;

    private final BuddyRepo buddyRepo;

    public int findAllEq(){

        return diveRep.findAll().stream()
                .flatMap(dive -> dive.getEquipmentList().stream()).sorted()
                .mapToInt(Equipment::getAmount)
                .sum();

    }



    public Optional<Duration> findLongestDuration() {
        return diveRep.findAll().stream()
                .map(dive -> dive.getDiverProfile().getDuration()).max(Comparator.naturalOrder());
    }

    public OptionalDouble getAvgConsumption() {
        return diveRep.findAll().stream()
                .mapToDouble(dive -> dive.getDiverProfile().getAvgConsuption())
                .average();
    }

    public Map<String, Integer> howManyTimeEachEqWasUsed() {
        return diveRep.findAll().stream()
                .flatMap(dive -> dive.getEquipmentList().stream())
                .collect(Collectors.groupingBy(
                        equipment -> equipment.getName(), //we gorupBy name here
                        Collectors.summingInt(Equipment::getAmount)//here what we do is collect the total amount per equipment not total maount in all dive e.g. "Pizza" appears twice and has 2 + 3 amount outcome is Pizza = 5 anount
                ));
    }

    public Map<String, Long> findUsageOfEqInDives() {
        return diveRep.findAllAsProjection().stream()
                .flatMap(diveProjection -> diveProjection.getEquipments().stream())
                .collect(Collectors.groupingBy(
                        EquipmentDTO::name,
                        Collectors.counting()
                ));
    }

    public List<Buddy> findBuddiesWithSpecificName(String searchTarget) {
        return buddyRepo.findAll().stream()
                .filter(buddy -> buddy.getName().equals(searchTarget))
                .collect(Collectors.toList());
    }

    public Optional<Equipment> findHighesteqAmount() {
        return diveRep.findAll().stream()
                .flatMap(dive -> dive.getEquipmentList().stream())
                .max((o1, o2) -> Integer.compare(o1.getAmount(), o2.getAmount()));
    }

    public List<Integer> getAllAmountsFromEq() {
        return diveRep.findAll().stream()
                .flatMap(dive -> dive.getEquipmentList().stream())
                .mapToInt(Equipment::getAmount).boxed().collect(Collectors.toList());
    }

    /*
    .flatMap() -- unravels a list  e.g. I want to get Equipment from Dive
    --> you can use flatMap to also extra multiple items e.g. .flatMap(ex -> Stream.of(ex.getName()), ex.getTime()).toList()
    .map() -- transforms things into a new value e.g. Equipment.name into a new name or .map(String::toUpperCase) to get uppercase
    --> .mapToInt() or .mapToDouble() can be used to convert to these values and extract example ints from Equipment
    --> you can use .sum() to get the sum of values or .average() for average
    --> after using .map() and extracting idk date you can then use .distinct() to remove duplciates

    .max() or .min() can be used to get the max value of a List e.g. .max(Comparator.naturalOrder()) <- ASC aka get the max

    .collect() -- puts things into a List e.g. .collect(Collectors.toList())
    .filter -- is used to well filter for something e.g. name .filter(eq -> eq.getName().equals("Bla")).colletc ...

    .anyMatch() -- finds any match lol .anyMatch(eq -> eq.getName().equals(name))

    .sorted(Comparator.comapre(lamba in here)) .sorted(Comparator.comapre(lamba in here).reversed())

    //GROUP BY (return type Map<..,..>)
    .collect(Collectors.groupingBy(<By What>, <how do you summ up>)) -- this can be used to let say find out how many
    of each Equipment exist in your List e.g.
    --> .collect(Collectors.groupingBy(eq -> eq.getName(), Collectors.count())

    if you want to get somethign like how many times something appears not in a list but by amount they you do
    --> .collect(Collectors.groupingBy(eq -> eq.getName(), Collectors.count()) yo dou Collectors.summingInt(Eq -> Eq.getAmount())

    //Practical example
    Let say you just wanna return the amount stated in each Eq. you do the stream things then
    .flatMap(dive -> dive.eqList().stream()) to unwrap it
    .mapToInt(Eq -> Eq.getAmount()) you specifically get the amount thats in Int
    .boxed().collect(Collectors.toList()) you put your results into a list

    .boxed() -- converts your .mapToInt into a properworking stream
     */
   //getDivesByDate, getBuddiesByType,getDivesWithHighConsumption, getLongestDive

    /*
    Task 1
     */
        public List<Dive> findByDate(LocalDate date) {
            return diveRep.findAll().stream()
                    .sorted(Comparator.comparing(Dive::getDate).reversed()).toList();
        }

    public List<DiveProjection> findByDateProjection(LocalDate date) {
        return diveRep.findAllAsProjection().stream()
                .sorted(Comparator.comparing(DiveProjection::getDate).reversed()) // Sort projections
                .toList();
    }

    public Optional<Dive> findLongestDive() {
        return diveRep.findAll().stream()
                .max((o1, o2) -> o1.getDiverProfile().getDuration().compareTo(o2.getDiverProfile().getDuration()));
    }
    /*
    Task 2
     */

    /*
    Task 3
     */

    /*
    Task 4
     */


}
