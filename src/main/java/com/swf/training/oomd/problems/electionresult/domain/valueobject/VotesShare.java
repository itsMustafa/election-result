package com.swf.training.oomd.problems.electionresult.domain.valueobject;

import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;

import java.util.Objects;

public class VotesShare {

    private final Party party;
    private final int votes;

    public VotesShare(Party party, int votes) throws InvalidParty, InvalidVotes {
        if (party == null) {
            throw new InvalidParty("Party cannot be null");
        } else if (votes < 0) {
            throw new InvalidVotes(String.valueOf(votes));
        } else {
            this.party = party;
            this.votes = votes;
        }
    }

    public Party getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotesShare that = (VotesShare) o;
        return votes == that.votes && party == that.party;
    }

    @Override
    public int hashCode() {
        return Objects.hash(party, votes);
    }
}
