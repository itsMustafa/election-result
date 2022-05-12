package com.swf.training.oomd.problems.electionresult.repositories.impl;

import com.swf.training.oomd.problems.electionresult.db.InMemoryConstituencyDB;
import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.repositories.ConstituencyRepository;

import java.util.List;

public class InMemoryConstituencyRepository implements ConstituencyRepository {

    private static InMemoryConstituencyRepository inMemoryConstituencyRepo;

    private final InMemoryConstituencyDB inMemoryConstituencyDB;

    private InMemoryConstituencyRepository(InMemoryConstituencyDB inMemoryConstituencyDB) {
        this.inMemoryConstituencyDB = inMemoryConstituencyDB;
    }

    public static InMemoryConstituencyRepository getConstituencyRepo(InMemoryConstituencyDB inMemoryConstituencyDB) {
        if (inMemoryConstituencyRepo == null) {
            synchronized (InMemoryConstituencyRepository.class) {
                inMemoryConstituencyRepo = new InMemoryConstituencyRepository(inMemoryConstituencyDB);
            }
        }
        return inMemoryConstituencyRepo;
    }


    @Override
    public List<Constituency> getConstituencies() {
        return inMemoryConstituencyDB.getConstituencyList();
    }

    @Override
    public void add(Constituency constituency) {
        inMemoryConstituencyDB.add(constituency);
    }
}
