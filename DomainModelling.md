# Domain Modelling

* ### Constituency: Value object
  * #### state: name, List< VotesShare >
  * #### behaviour: getter

* ### PartyCode: enum-Value object
  * #### state: name, fullname
  * #### behaviour: getFullName

* ### VotesShare: ValueObject
	* #### state: PartyCode, votes

* ### ResultCalculator:
  * #### behaviour: calculateResult(list< VotesShare >)
