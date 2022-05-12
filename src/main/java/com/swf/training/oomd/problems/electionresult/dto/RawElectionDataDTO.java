package com.swf.training.oomd.problems.electionresult.dto;

import java.util.Arrays;
import java.util.Objects;

public class RawElectionDataDTO {

    private final String constituency;
    private final String[] partyAndVotes;

    public RawElectionDataDTO(String constituency, String[] partyAndVotes) {
        this.constituency = constituency;
        this.partyAndVotes = partyAndVotes;
    }

    public String getConstituency() {
        return constituency;
    }

    public String[] getPartyAndVotes() {
        return partyAndVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawElectionDataDTO that = (RawElectionDataDTO) o;
        return Objects.equals(constituency, that.constituency) && Arrays.equals(partyAndVotes, that.partyAndVotes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(constituency);
        result = 31 * result + Arrays.hashCode(partyAndVotes);
        return result;
    }
}
