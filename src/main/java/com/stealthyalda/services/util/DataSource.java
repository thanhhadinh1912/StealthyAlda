package com.stealthyalda.services.util;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataSource {
    private static DataSource instance = new DataSource();
    private List<StellenanzeigeDTO> liste;

    private DataSource() {
        liste = new ArrayList<>();

    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public List<StellenanzeigeDTO> getListe() {
        return liste;
    }

    public void loadsuche(String titelorunternehmen, String ort) {
        liste = StellenanzeigeDAO.getInstance().getStellenanzeigeByLocationAndJobTitelOrUnternehmen(titelorunternehmen, ort);

    }

    public Stream<StellenanzeigeDTO> fetchJoborArbeitgeber(StellenanzeigeDTO filter, int offset, int limit) {
        return getListe().stream()
                .filter(joborarbeitgeber -> filter == null || joborarbeitgeber.getTitel().toLowerCase().startsWith(filter.getTitel().toLowerCase())
                        || joborarbeitgeber.getArbeitgeber().toLowerCase().startsWith(filter.getTitel().toLowerCase())
                )
                .skip(offset).limit(limit);
    }

    public Stream<StellenanzeigeDTO> fetchOrt(StellenanzeigeDTO filter, int offset, int limit) {
        return getListe().stream()
                .filter(ort -> filter == null || ort.getOrt()
                        .toLowerCase().startsWith(filter.getOrt().toLowerCase())
                )
                .skip(offset).limit(limit);
    }
}
