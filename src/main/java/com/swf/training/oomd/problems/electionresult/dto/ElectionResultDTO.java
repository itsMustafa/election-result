package com.swf.training.oomd.problems.electionresult.dto;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVoteShare;

import java.util.Objects;

public class ElectionResultDTO {

    private final String constituencyName;
    private final Party party;
    private final double votesShare;

    public ElectionResultDTO(String constituencyName, Party party, double votesShare) throws InvalidConstituency, InvalidParty, InvalidVoteShare {
        if (constituencyName.equals("")) {
            throw new InvalidConstituency("Constituency name cannot be empty");
        } else if (party == null) {
            throw new InvalidParty("Party cannot be null");
        } else if (votesShare < 0) {
            throw new InvalidVoteShare(String.valueOf(votesShare));
        } else {
            this.constituencyName = constituencyName;
            this.party = party;
            this.votesShare = votesShare;
        }
    }

    public String getConstituency() {
        return constituencyName;
    }

    public Party getParty() {
        return party;
    }

    public double getVotesShare() {
        return votesShare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectionResultDTO electionResultDTO = (ElectionResultDTO) o;
        return Double.compare(electionResultDTO.votesShare, votesShare) == 0 && Objects.equals(constituencyName, electionResultDTO.constituencyName) && party == electionResultDTO.party;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constituencyName, party, votesShare);
    }
}
