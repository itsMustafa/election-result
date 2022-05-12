package com.swf.training.oomd.problems.electionresult.builder;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;

public class PartyAndVotesBuilder {

    public static VotesShare partyAndVotesDTO(Party party, int votes) throws InvalidParty, InvalidVotes {
        return new VotesShare(party, votes);
    }
}
