package com.swf.training.oomd.problems.electionresult.domain.service;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;

import java.util.Comparator;

public class ElectionResultCalculator {

    public VotesShare getPartyWithMaxVotes(Constituency constituency) {
        return constituency.getPartyAndVotes()
                .stream()
                .max(Comparator.comparingInt(VotesShare::getVotes))
                .get();
    }

    private int getTotalVotes(Constituency constituency) {
        return constituency.getPartyAndVotes().stream().mapToInt(VotesShare::getVotes).sum();
    }

    private int getWinningVotes(Constituency constituency) {
        return getPartyWithMaxVotes(constituency).getVotes();
    }

    public double getVotesShare(Constituency constituency) {

        int sum = getTotalVotes(constituency);

        int winVotes = getWinningVotes(constituency);

        return (((double) winVotes / (double) sum) * 100);

    }

}
