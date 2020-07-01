package com.stealthyalda.services.util;

import com.stealthyalda.ai.model.dao.SearchDAO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;

import java.util.List;
import java.util.stream.Stream;

public class SearchArbeitgeberServiceOhneBewerbung {
    private static Arbeitgeber a;
    private static List<String> liste = null;

    public SearchArbeitgeberServiceOhneBewerbung(Arbeitgeber a) {
        SearchArbeitgeberServiceOhneBewerbung.a = a;
        liste = SearchDAO.getInstance().getStellenanzeigeFuerArbeitgeber(a);
    }


    public List<String> getStellenanzeige() {
        return liste;
    }

    public int count() {
        return liste.size();
    }

    public int count(String filter) {
        return (int) getStellenanzeige().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase())
                        || job.toLowerCase().contains(filter.toLowerCase())
                )
                .count();
    }

    public Stream<String> fetch(String filter, int offset, int limit) {
        return getStellenanzeige().stream()
                .filter(job -> filter == null || job
                        .toLowerCase().startsWith(filter.toLowerCase()) || job
                        .toLowerCase().contains(filter.toLowerCase())
                )
                .skip(offset).limit(limit);
    }
}
