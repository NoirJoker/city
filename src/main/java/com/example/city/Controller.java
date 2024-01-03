package com.example.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private static final String INVALID = "INVALID";

  @Autowired
  private JmsTemplate jmsTemplate;
  @Autowired
  private Queue queue;
  @Autowired
  private CityRepository cityRepository;

  @GetMapping("/cities")
  public List<City> listCities() {
    List<City> cities = getCities();
    findNames(cities);
    sendToQueue(cities);
    return cities;
  }

  /**
   * todo: read cities from database
   */
  List<City> getCities() {
    return cityRepository.findAll();
  }

  /**
   * todo: resolve the puzzles and set the resolved solution to city.name
   */
  void findNames(List<City> cities) {
    cities.forEach(city -> city.name = findName(city.puzzle));
  }

  private String findName(String puzzle) {
    Map<String, List<String>> x = new HashMap<>();
    Map<String, List<Boolean>> y = new HashMap<>();

    int count = 0;
    for (String p : puzzle.split(",")) {
      count++;
      String a = p.charAt(0) + "";
      String b = p.charAt(p.length() - 1) + "";
      x.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
      y.computeIfAbsent(a, k -> new ArrayList<>()).add(false);
    }

    Set<String> names = new HashSet<>();
    for (String start : x.keySet()) {
      search(start, start, x, y, names, count);
    }

    return names.isEmpty() ? INVALID : String.join(",", names);
  }

  private void search(String name, String next, Map<String, List<String>> x, Map<String, List<Boolean>> y, Set<String> names, int count) {
    List<String> xList = x.getOrDefault(next, Collections.emptyList());
    List<Boolean> yList = y.getOrDefault(next, Collections.emptyList());
    for (int i = 0; i < xList.size(); i++) {
      if (!yList.get(i)) {
        yList.set(i, true);
        search(name + xList.get(i), xList.get(i), x, y, names, count);
        yList.set(i, false);
      }
    }
    if (name.length() == count + 1) {
      names.add(name);
    }
  }

  /**
   * todo: send resolved city name (if it's not invalid) to the queue configured in CityApplication.class. Each message only contains one city's name(s).
   */
  void sendToQueue(List<City> cities) {
    cities.stream()
        .map(c -> c.name)
        .filter(name -> !Objects.equals(name, INVALID))
        .forEach(name -> jmsTemplate.convertAndSend(queue, name));
  }

}
