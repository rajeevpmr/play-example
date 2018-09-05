package domain;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class MyShop implements Shop {

    SortedMap<Long, Item> items = new ConcurrentSkipListMap<>();
    AtomicLong sequence = new AtomicLong();

    @Override
    public List<Item> list() {

        return new ArrayList<>(this.items.values());
    }

    @Override
    public Item create(String name, Double price) {

        Long id = sequence.incrementAndGet();
        Item item = new Item(id, name, price);
        this.items.put(id, item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return this.items.get(id);
    }

    @Override
    public Item update(Long id, String name, Double price) {

        Item oldItem = this.get(id);

        Item updated = new Item(id, name, price);
        this.items.put(id, updated);

        return updated;
    }

    @Override
    public Boolean delete(Long id) {

        return this.items.remove(id)!= null;

    }
}
