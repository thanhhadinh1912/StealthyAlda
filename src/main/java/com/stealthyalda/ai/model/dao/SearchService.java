package com.stealthyalda.ai.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SearchService {
    private static List<String> liste = new ArrayList<>();

    public SearchService() {
        liste = SearchDAO.getInstance().getJobtitelOrArbeitgeber();
    }

    public List<String> getJobtitelOrArbeitgeber() {
        return liste;
    }

    public int count() {
        return liste.size();
    }

    public int count(String filter) {
        return (int) getJobtitelOrArbeitgeber().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase())
                        || job.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getJobtitelOrArbeitgeber().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase()) || job
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }


}

