package domain;

import domain.Item;

import java.util.List;

public interface Shop {

    List<Item> list();
    Item create(String name, Double price);
    Item get(Long id);
    Item update(Long id, String name, Double price);
    Boolean delete(Long id);

}
