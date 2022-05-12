package com.swf.training.oomd.problems.electionresult.repositories;

import com.swf.training.oomd.problems.electionresult.db.InMemoryConstituencyDB;
import com.swf.training.oomd.problems.electionresult.domain.entity.Constituency;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.Party;
import com.swf.training.oomd.problems.electionresult.domain.valueobject.VotesShare;
import com.swf.training.oomd.problems.electionresult.error.InvalidConstituency;
import com.swf.training.oomd.problems.electionresult.error.InvalidParty;
import com.swf.training.oomd.problems.electionresult.error.InvalidPartyAndVotesDTO;
import com.swf.training.oomd.problems.electionresult.error.InvalidVotes;
import com.swf.training.oomd.problems.electionresult.repositories.impl.InMemoryConstituencyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.swf.training.oomd.problems.electionresult.builder.PartyAndVotesBuilder.partyAndVotesDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstituencyRepositoryTest {

    private ConstituencyRepository constituencyRepository;

    @BeforeEach
    void setUp() {
        constituencyRepository = InMemoryConstituencyRepository.getConstituencyRepo(InMemoryConstituencyDB.getConstituencyDB());
    }

    @AfterEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field inMemoryConstituencyDB = InMemoryConstituencyDB.class.getDeclaredField("inMemoryConstituencyDB");
        inMemoryConstituencyDB.setAccessible(true);
        inMemoryConstituencyDB.set(null, null);

        Field inMemoryConstituencyRepo = InMemoryConstituencyRepository.class.getDeclaredField("inMemoryConstituencyRepo");
        inMemoryConstituencyRepo.setAccessible(true);
        inMemoryConstituencyRepo.set(null, null);

        this.constituencyRepository = null;
    }

    @Test
    void shouldBeAbleToAddConstituency() throws InvalidParty, InvalidVotes, InvalidPartyAndVotesDTO, InvalidConstituency {
        List<VotesShare> votesShares = new ArrayList<>(List.of(partyAndVotesDTO(Party.BJP, 100)));

        constituencyRepository.add(new Constituency("myConstituency1", votesShares));
        constituencyRepository.add(new Constituency("myConstituency2", votesShares));

        assertEquals(2, constituencyRepository.getConstituencies().size());
    }

    @Test
    void shouldBeAbleToGetConstituencyList() throws InvalidPartyAndVotesDTO, InvalidConstituency, InvalidParty, InvalidVotes {

        List<VotesShare> votesShares = List.of(
                partyAndVotesDTO(Party.BJP, 100),
                partyAndVotesDTO(Party.BSP, 100),
                partyAndVotesDTO(Party.CPI, 100)
        );

        constituencyRepository.add(new Constituency("myConstituency1", votesShares));
        constituencyRepository.add(new Constituency("myConstituency2", votesShares));

        List<Constituency> expectedConstituencies = new ArrayList<>(
                List.of(
                        new Constituency("myConstituency1", votesShares),
                        new Constituency("myConstituency2", votesShares)
                )
        );
        assertEquals(expectedConstituencies, constituencyRepository.getConstituencies());

    }

    @Test
    void shouldGetOnlyOneInstanceOfTheRepository() {
        ConstituencyRepository constituencyRepository1 = InMemoryConstituencyRepository.getConstituencyRepo(InMemoryConstituencyDB.getConstituencyDB());
        ConstituencyRepository constituencyRepository2 = InMemoryConstituencyRepository.getConstituencyRepo(InMemoryConstituencyDB.getConstituencyDB());

        assertEquals(constituencyRepository1, constituencyRepository2);
    }
}
