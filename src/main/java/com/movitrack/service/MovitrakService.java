package com.movitrack.service;

import com.movitrack.client.DBClient;
import com.movitrack.model.Item;
import com.movitrack.model.client.DBClientConfig;
import com.movitrack.client.WebClientConfig;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovitrakService {

    private final DBClient dbClient;
    private final Map<Integer, Item> store = new HashMap<>();

    public MovitrakService() {
        dbClient = new DBClient(new WebClientConfig());
    }

    public List<Item> getAll() {
        return new ArrayList<>(store.values());
    }

    public List<Item> refresh() {
        populateStore();
        return new ArrayList<>(store.values());
    }

    private void populateStore() {
        store.clear();
        List<Item> items = dbClient.refreshData().block();
        assert items != null;
        for(Item item : items)
            store.put(item.getId(), item);
    }

}
