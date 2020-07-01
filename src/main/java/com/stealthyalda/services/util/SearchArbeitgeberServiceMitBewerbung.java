package com.stealthyalda.services.util;

import com.stealthyalda.ai.model.dao.SearchDAO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;

import java.util.List;
import java.util.stream.Stream;

public class SearchArbeitgeberServiceMitBewerbung {

    private static Arbeitgeber a;
    private static List<String> liste = null;

    public SearchArbeitgeberServiceMitBewerbung(Arbeitgeber a) {
        SearchArbeitgeberServiceMitBewerbung.a = a;
        liste = SearchDAO.getInstance().getBewerber(a);
    }


    public List<String> getBewerber() {
        return liste;
    }

    public int count() {
        return liste.size();
    }

    public int count(String filter) {
        return (int) getBewerber().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase())
                        || job.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getBewerber().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase()) || job
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }


}
