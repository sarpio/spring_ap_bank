# AP BANK Spring Demo application
## Application consists of the following components:
- Eureka Service
- Config Service
- Gateway Service
- Account Service
- Customer Service
- Operation Service

## Method types in Controller pattern:

- Get all: findAll`component`
- Get One: find`component`By`reference`
- Put One: update`component`By`reference`
- Post one: save`component`
- Delete one: delete`component`By`reference`

### Sample:

Get one: finAccountById _where_:
`compnent` is "Account" and
`reference` is "Id"

## Configuration Service
Default configuration file in services have been replaced by `bootstrap.yml`
File contains only basic configuration required to be available locally by service only.
All other configuration is has been in `configruation` _folder_ inb application `root`.
