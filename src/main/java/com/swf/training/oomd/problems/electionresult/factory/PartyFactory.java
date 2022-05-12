package com.swf.training.oomd.problems.electionresult.factory;

import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;

public class PartyFactory {

    public Party createFrom(String rawParty) throws InvalidParty {
        if (Party.isValidPartyCode(rawParty)) {
            return Party.valueOf(rawParty);
        } else {
            throw new InvalidParty(rawParty);
        }
    }
}
