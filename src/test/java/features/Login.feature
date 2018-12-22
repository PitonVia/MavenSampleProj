Feature: Application Login

Scenario Outline: Positive test validating login
Given Open Chrome browser
And Navigate to the "http://qaclickacademy.com" website Home page
And Click on the Login button on Home page to land on Sign in page
When User inputs valid user name <username> and password <password> and clicks on the login button
Then Verify that user successfully logs in
And Close the browsers

Examples:
|username				|password	|
|piton.via@gmail.com	|Testing123	|
|test99@gmail.com		|123456		|