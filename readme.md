#Vector Users CRUD App

Hi team, thanks for reviewing! 

I am using a postgres database, with spring JPA. This is my first spring application, and first time working with beans. It was pretty interesting to look at, and I found it fun, but there were a couple things I couldn't figure out.

Spring should create the table for you, so as long as you give it a valid database the API should work. However I did include the sql schema in users.sql if needed.

1) I am still not familiar with the 'Dao' pattern that most spring examples are using, so I used something farmiliar with the DTO pattern.

2) I am not fully familiar with how exceptions are being handled in this framework, normally I would inject some high level middleware to catch everything and start creating my own handlers, but this pattern was not very friendly to use in spring.

There was a few more, but mostly around differences between .NET and Spring.

I have tried changing the error response message through application.yml, and creating a custom handler/controller advice but I couldn't figure out how to get it to catch everything. Only how to catch specific exceptions.

Short of wrapping each controller function in their own catch blocks or handling each exception in my own controller advice, I'm not sure how to achieve the custom error messages at this stage sorry.
