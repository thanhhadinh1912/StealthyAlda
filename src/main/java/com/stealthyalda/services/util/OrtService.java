package com.stealthyalda.services.util;

import com.stealthyalda.ai.model.dao.SearchDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrtService {
    private static List<String> liste = new ArrayList<>();

    public OrtService() {
        liste = SearchDAO.getInstance().getOrt();
    }

    public List<String> getOrt() {
        return liste;
    }

    public int count() {
        return liste.size();
    }

    public int count(String filter) {
        return (int) getOrt().stream()
                .filter(ort -> filter == null || ort
                        .toLowerCase().startsWith(filter.toLowerCase())
                        || ort.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getOrt().stream()
                .filter(ort -> filter == null || ort
                        .toLowerCase().startsWith(filter.toLowerCase()) || ort
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }
}
