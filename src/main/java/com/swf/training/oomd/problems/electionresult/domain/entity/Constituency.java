package com.swf.training.oomd.problems.electionresult.domain.entity;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidPartyAndVotesDTO;

import java.util.List;
import java.util.Objects;

public class Constituency {

    private final String name;
    private final List<VotesShare> votesShares;

    public Constituency(String name, List<VotesShare> votesShares) throws InvalidConstituency, InvalidPartyAndVotesDTO {
        if (name.equals("")) {
            throw new InvalidConstituency(name);
        } else if (votesShares.isEmpty()) {
            throw new InvalidPartyAndVotesDTO("PartyAndVotesDTO cannot be empty");
        } else {
            this.name = name;
            this.votesShares = votesShares;
        }
    }

    public String getName() {
        return name;
    }

    public List<VotesShare> getPartyAndVotes() {
        return votesShares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constituency that = (Constituency) o;
        return name.equals(that.name) && votesShares.equals(that.votesShares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, votesShares);
    }

}
