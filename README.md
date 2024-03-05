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

1. Make sure Java JDK, Maven and other installed. 
2. Run command from root directory (were pom.xml and mvnw. files located)
```
./mvnw spring-boot:run
```

## Usage

1. IBAN Validation endpoint & description
<br/>
Example output:

```

AA051245445454552117989     Invalid
LT647044001231465456        Valid
LT517044077788877777        Valid
LT227044077788877777        Invalid
CC051245445454552117989     Invalid

```

2. Bank Recognition endpoint & description
<br/>
Example output:

```

AA051245445454552117989     -
LT647044001231465456        Bank1
LT517180077788877777        Bank2
LT227044077788877777        SEB
CC051245445454552117989     -

```

## License

[LICENSE](LICENSE) will be added later.

## Documentation

Additional documentation will be available [here](link) in future.

## Build and Deployment

Docker instructions will be described in the future.

## Testing

Testing instructions will be described in the future.

## Contact

For being in touch, please contact me, Jegor Petsorin, at [egorpechoring@gmail.com](mailto:egorpechoring@gmail.com) or connect on [LinkedIn](https://www.linkedin.com/in/jegor-petsorin/).

