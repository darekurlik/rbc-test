**Original requirements:**
Write a program that takes a basket of items and outputs its total cost.
The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches

**My assumptions:**
1. Application needs to know what are the items and what is their cost. 
I took a liberty of providing that information in form of catalog file provided on start up.
This solution provide flexibility for adding new items.
2. Form of interaction with the user was not specified.
 I chose to perform the interaction with the user trough console.
 
**Comments:** 
- RBCIntegrationTest is a good place to start. It creates temp catalog file that its passed as program argument. 
