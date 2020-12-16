# Subscription
A simple subscription service

HOW TO INSTALL: Run command "mvn install"

HOW TO RUN: Left click on "EzypayApplication" and run as "Java Application"

POST: http://localhost:8080/subscription/submit

Weekly Request Data
{
    "amount": "1000",
    "amountCurrency": "MYR",
    "subscriptionType": "W",
    "subscriptionTime": "TUESDAY",
    "startDate": "16/12/2020",
    "endDate": "16/01/2021"
}

MONTHLY Request Data
{
    "amount": "1000",
    "amountCurrency": "MYR",
    "subscriptionType": "M",
    "subscriptionTime": "28",
    "startDate": "16/12/2020",
    "endDate": "16/02/2021"
}
