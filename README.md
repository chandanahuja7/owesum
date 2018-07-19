# OweSum - Automate your debt settlements
> Beware of little expenses; a small leak will sink a great ship -  Benjamin Franklin

OweSum (Pronounced "Awesome") is a small Microservices based App that helps users/groups in managing their expenses, debts and its recovery.

Often a group of people divide amongst each other the expenses for events/journeys/get-togethers/flat-expenses etc. For convenience, only one or few amongst a group, make the "payments" for the entire group and subsequently the rest of the group-members need to pay back their own individual-share to the person(s) who made the payments. 

For short journeys/events involving small size of group the payments and the subsequest paybacks can be managed manually, but often the calculation becomes complex and a need is felt to automate this entire process. OweSum is the tool which helps in making this possible.

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
  - Server : Jetty
  - Spring Boot :  	Quick bootstrapping, Out of the box features for concerns like Logging, App-Configuration, Health , Monitoring, Cloud support etc
  - PostgreSQL DB 9.6 +  : Open Source, best of breed, with JSONB type provides best of both RDBMS as well as NoSQL world. 
  - Spring MVC : Easy to implement REST support with annotations.
  - Spring JDBC : Very light weight , more control to developers, performant , wanted to avoid ORM's as it might not be very performant.
  - Tomcat connection Pooling : DB Connection pooling
  - Faster Jackson: JSON binding
  - Log4J : Logging 
  - Maven : Build Tool
  - TODO: Netflix Eureka : For Service Registry
  - TODO : Netflix Zuul : API Gateway





# Getting Started

Check the folder "gettingstarted" for more resources

At present only 2 microservices are developed viz. 


- Payment Microservice : CRUD Operations for the Payments for a given Event. DAO for  been built for each of the CRUD operation but only 2 REST end points viz. add and read have been added .
 
- Report Microservice  : It provides only Readonly features. Given an EvenId

- About Other micro Services like Event and User were just needed for CRUD operations. They are not core like the Payment and Report so they have been not prioritised till now. They will be added on in sometime. the Event and Party tables have neot been populated. The foreigh keys values of Event and Party in Payment and Paymentbeneficy table have been hard coded as of now (FK's have not been added on them ) . For ease of Testing the "Id"/Primary-Key of each entity in Event and Party have been appropriately given readable values example event_id is "triptobikaner"  similarly 4 Parties have their party_id's as ram,shyam, shiv and mohan. These Id's doubleup as Names and thus they have not been joined back with Party and Event Tables.


### Payment Microservice

- http://<IP/hostname>:7777/payments   : Adds a New Payment and associated Payment Beneficiary. 
Check folder JSONInputs,  file triptobikanerExpenses.json for inserting test data

- http://<IP/hostname>:7777/payments/{paymentId}   : Returns JSON, with all the Payment and the PaymentBeneficiary Details.
Ex. http://localhost:7777/payments/PA_v9R3uY_ET1q81AAeBbq9_w


### Report Microservice

- http://<IP/hostname>:7778/reports/{eventId} : This shall return the Expense report with following information for each party in the event.
- Who  owes and the amount
- who is Owed and the amount
- Total Payment made by each Party 
- And MOST IMPORTANTLY - the suggested payment which need to be made to even out the debt.
- URL Ex. http://localhost:7778/reports/triptobikaner   . This shall work if the JSON specified in JSONInputs/triptobikanerExpenses.json are added using the Payments Add URL mentioned above (http://<IP/hostname>:7777/payments).


- Real Reports  as generated on local machine for http://localhost:7778/reports/triptobikaner  :

```` json
{  
   "event":{  
      "eventId":"triptobikaner",
      "parties":[  

      ]
   },
   "owesumDetailsSet":[  
      {  
         "party":{  
            "partyId":"mohan"
         },
         "totalSpent":0,
         "isOwed":0,
         "owes":120.00,
         "balance":-120.00
      },
      {  
         "party":{  
            "partyId":"shiv"
         },
         "totalSpent":0,
         "isOwed":0,
         "owes":110.00,
         "balance":-110.00
      },
      {  
         "party":{  
            "partyId":"shyam"
         },
         "totalSpent":200.00,
         "isOwed":100.00,
         "owes":90.00,
         "balance":10.00
      },
      {  
         "party":{  
            "partyId":"ram"
         },
         "totalSpent":360.00,
         "isOwed":270.00,
         "owes":50.00,
         "balance":220.00
      }
   ],
   "suggestedPaymentsSet":[  
      {  
         "purposeTpCd":7777,
         "paidByPartyId":"mohan",
         "paymentAmount":120.00,
         "paymentBeneficiaries":[  
            {  
               "beneficiaryPartyId":"ram",
               "benefittedAmount":120.00
            }
         ]
      },
      {  
         "purposeTpCd":7777,
         "paidByPartyId":"shiv",
         "paymentAmount":100.00,
         "paymentBeneficiaries":[  
            {  
               "beneficiaryPartyId":"ram",
               "benefittedAmount":100.00
            }
         ]
      },
      {  
         "purposeTpCd":7777,
         "paidByPartyId":"shiv",
         "paymentAmount":10.00,
         "paymentBeneficiaries":[  
            {  
               "beneficiaryPartyId":"shyam",
               "benefittedAmount":10.00
            }
         ]
      }
   ]
}

````


# Acknowledgements
Thanks to the Open source contributors for making lovely frameworks for larger Software engineers fraternity.


