# IBAN Checker

Java Spring Boot MVC REST Web API service for International Bank Account Numbers (IBANs) validation and corresponding bank information providing. <br/>
Code is written according to SOLID and DRY principles in a TDD-like paradigm. 

Key Functionality: 
- IBAN Validation - Given a list of IBANs, the webservice validates each IBAN and returns whether it is valid or invalid.

- Bank Recognition - Given a list of IBANs, the webservice identifies the corresponding banks, recognizing SEB and two other banks.

[Getting Started](#getting-started) <br/>
[Usage](#usage) <br/>
[License](#license) <br/>
[Documentation](#documentation) <br/>
[Build and Deployment](#build-and-deployment) <br/>
[Testing](#testing) <br/>
[Contact](#contact) <br/>


## Getting Started

1. Make sure Java JDK, Maven and Spring Boot installed and you have IDE.
2. Clon this repository.
3. Run command from root directory (were pom.xml and mvnw. files located)
```
./mvnw spring-boot:run
```

Or run via Docker, instructions from [Build and Deployment](#build-and-deployment) section.

## Usage
### IBAN Validation 
* Method `POST`

* Endpoint url `http://localhost:8080/api/secure/iban/validate`

* Example json body:
```

{
    "data": [
        "AA051245445454552117989",
        "LT647044001231465456",
        "LT517044077788877777",
        "LT227044077788877777",
        "CC051245445454552117989"
    ]
}

```
* Example output:
```

{
    "data": [
        {
            "iban": "AA051245445454552117989",
            "status": false
        },
        {
            "iban": "LT647044001231465456",
            "status": true
        },
        {
            "iban": "LT517044077788877777",
            "status": true
        },
        {
            "iban": "LT227044077788877777",
            "status": false
        },
        {
            "iban": "CC051245445454552117989",
            "status": false
        }
    ]
}

```

### Bank Recognition
* Method `POST`

* Endpoint url `http://localhost:8080/api/secure/iban/recognize`

* Example json body:
```

{
    "data" : [
        "AA051245445454552117989",
        "LT647044001231465456",
        "LT517180077788877777", 
        "LT227044077788877777",
        "CC051245445454552117989"
    ]
}
```

* Example output:
```

{
    "data": [
        {
            "iban": "AA051245445454552117989",
            "bank": "-"
        },
        {
            "iban": "LT647044001231465456",
            "bank": "SEB"
        },
        {
            "iban": "LT517180077788877777",
            "bank": "Siauliu"
        },
        {
            "iban": "LT227044077788877777",
            "bank": "SEB"
        },
        {
            "iban": "CC051245445454552117989",
            "bank": "-"
        }
    ]
}

```

## Documentation

Additional documentation will be available [here](link) in future.

## Build and Deployment

1. Build an Docker image 
```
docker build -t iban-app .  
```
2. Run a Docker container
```
docker run -p 8080:8080 iban-app
```

## Testing

- Run tests manually from 'Testing' tab
- Or run tests from terminal:
```
mvn test
```

## Contact

For being in touch, please contact me, Jegor Petsorin, at [egorpechoring@gmail.com](mailto:egorpechoring@gmail.com) or connect on [LinkedIn](https://www.linkedin.com/in/jegor-petsorin/).

