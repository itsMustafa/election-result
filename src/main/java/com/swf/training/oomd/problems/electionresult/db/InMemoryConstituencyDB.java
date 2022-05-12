package com.swf.training.oomd.problems.electionresult.db;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;

import java.util.ArrayList;
import java.util.List;

public class InMemoryConstituencyDB {

    private static InMemoryConstituencyDB inMemoryConstituencyDB;

    private final List<Constituency> constituencyList;

    private InMemoryConstituencyDB() {
        constituencyList = new ArrayList<>();
    }

    public static InMemoryConstituencyDB getConstituencyDB() {
        if (inMemoryConstituencyDB == null) {
            synchronized (InMemoryConstituencyDB.class) {
                inMemoryConstituencyDB = new InMemoryConstituencyDB();
            }
        }
        return inMemoryConstituencyDB;
    }

    public List<Constituency> getConstituencyList() {
        return constituencyList;
    }

    public void add(Constituency constituency) {
        constituencyList.add(constituency);
    }
}
