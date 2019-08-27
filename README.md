# Registration

### Technologies used:
* Gradle v4.7
* Java v8
* Springboot
* Junit
* Mockito

### Assumptions made:
The date of birth provided by the exclusions list was in the incorrect format, this was subsequently modified

### The project can run by executing the run.sh file found within the base directory

#### Curl command for a valid request

curl --header "Content-Type: application/json" --request POST --data '{"username": "janedoe","password": "heLLoW00rld", "dateOfBirth": "1994-12-10","socialSecurityNumber": "85385072"}' http://localhost:8080/register

_A valid request will return a 200 code as well as a sanitized version of the user registered_

### In order to post a valid user (http://localhost:8080/register) the following JSON body can be used:
{
    "username": "johnode",
    "password": "heLLoW00rld",
    "dateOfBirth": "1917-12-10",
    "socialSecurityNumber": "85385073"
}

### Improvements to be made
* First and foremost the test coverage would need to be expanded
* Tests would need to look for explicit error responses and not just error codes 

