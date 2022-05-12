package com.swf.training.oomd.problems.electionresult.db;

import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidPartyAndVotesDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.swf.training.oomd.problems.electionresult.builder.PartyAndVotesBuilder.partyAndVotesDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryConstituencyDBTest {

    InMemoryConstituencyDB constituencyDB;

    @BeforeEach
    void setUp() {
        this.constituencyDB = InMemoryConstituencyDB.getConstituencyDB();
    }

    @AfterEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field inMemoryConstituencyDB = InMemoryConstituencyDB.class.getDeclaredField("inMemoryConstituencyDB");
        inMemoryConstituencyDB.setAccessible(true);
        inMemoryConstituencyDB.set(null, null);
        this.constituencyDB = null;
    }

    @Test
    void shouldBeAbleToCreateEmptyList() {
        constituencyDB = InMemoryConstituencyDB.getConstituencyDB();
        assertTrue(constituencyDB.getConstituencyList().isEmpty());
    }

    @Test
    void shouldBeAbleToAddConstituency() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {

        List<VotesShare> votesShares = new ArrayList<>(List.of(partyAndVotesDTO(Party.BJP, 100)));

        constituencyDB.add(new Constituency("myConstituency1", votesShares));
        constituencyDB.add(new Constituency("myConstituency2", votesShares));

        assertEquals(2, constituencyDB.getConstituencyList().size());
    }

    @Test
    void shouldBeAbleToGetConstituencyList() throws InvalidPartyAndVotesDTO, InvalidConstituency, InvalidParty, InvalidVotes {

        List<VotesShare> votesShares = new ArrayList<>(
                List.of(
                        partyAndVotesDTO(Party.BJP, 100),
                        partyAndVotesDTO(Party.BSP, 100),
                        partyAndVotesDTO(Party.CPI, 100)
                )
        );

        constituencyDB.add(new Constituency("myConstituency1", votesShares));
        constituencyDB.add(new Constituency("myConstituency2", votesShares));

        List<Constituency> expectedConstituencies = new ArrayList<>(
                List.of(
                        new Constituency("myConstituency1", votesShares),
                        new Constituency("myConstituency2", votesShares)
                )
        );
        assertEquals(expectedConstituencies, constituencyDB.getConstituencyList());

    }

}
