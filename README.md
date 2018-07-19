# OweSum - Automate your debt settlements
> Beware of little expenses; a small leak will sink a great ship - by Benjamin Franklin

OweSum (Pronounced "Awesome") is a small Microservices based App that helps users/groups in managing their expenses, debts and its recovery.

Often a group of people divide amongst each other the expenses for events/journeys/get-togethers/flat-expenses etc. For convenience, only one or few amongst a group, make the "payments" for the entire group and subsequently the rest of the group-members need to pay back their own individual-share to the person(s) who made the payments. 

For short journeys/events involving small size of group the payments and the subsequest paybacks can be managed manualaly, but often the calculation becomes complex and a need is felt to automate this entire process. OweSum is the tool which helps in making this possible.

OweSum helps the users/groups in following ways :

  - Tracks expense-sharing Parties/Participants involved in a group/Event/Journey.
  - Tracks Payments made by Participants and also on whose behalf they were made.
  - Payments can be divided equally or unequally amongst the participants.
  - Suggest the Best way in which the debt can be settled with minimum transactions.
  - Reports who owes what amount to whom.
  - Reports total expenses made by a User
  - Reports total expenses made for a event
  - TODO: Payment settlement through peer-to-peer payments
  - TODO: Categorywise Expense breakup
  - TODO: integrating with socialmedia to export friends  or send invitations for events


# Architecture 

Bulit using Microservices architectural stype , it divides a problem in various smaller domains , each domain area is a potential Microservies.
the bunch of microservices thus produces work in clooaboration with eachother to address the larger problem.
Microservices has host of benefits over traditional moloithic kinds of architecture. Google Microservices architecture to figure out pros and cons of Microservice architectural style.

# Tech-Stack

Built on latest Open source technology stack 

  - Java 8+ : Latest features, foreach, Lambda
  - Spring Boot :  	Quick bootstrapping, Out of the box features for concerns like Logging, App-Configuration, Health , Monitoring, Cloud support etc
  - PostgreSQL DB 9.6 +  : Open Source, best of breed, with JSONB type provides best of both RDBMS as well as NoSQL world. 
  - Spring MVC : Easy to implement REST support with annotations.
  - Spring JDBC : Very light weight , more control to developers, performant , wanted to avoid ORM's as it might not be very performant.
  - Faster Jackson: JSON binding
  - Log4J : Logging 


# Acknowledgements
Thanks to the Open source contributors for making lovely frameworks for larger Softare engineers fraternity.


