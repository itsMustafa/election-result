package com.swf.training.oomd.problems.electionresult.repositories;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;

import java.util.List;

public interface ConstituencyRepository {

    List<Constituency> getConstituencies();

    void add(Constituency constituency);

}
