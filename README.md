
# Country Restful API (programming challenge)

This project was created as programming challege for 2ibi, it is a country restful api which contains 7 endpoints that allows for :
- Creating new countires
- Deleting existing countries
- Updating existing countries
- Viewing a list of all countries
- Viewing a sorted list of all countries
- Fetching a single country by ID 
- Fetching a single country by name

All endpoints and respective business logic have been tested and API references can be located at https://countryrestapi2ibi.herokuapp.com/swagger-ui.html

## Specifications

### Project Type
This is a spring based project making use of the springboot framework

### Build automation tools
This project makes use of gradle as its build automation tool

### Persistent Storage
This projects makes use an embbed in memory database, particularly  H2 Database


## API
The API url is https://countryrestapi2ibi.herokuapp.com and can be used to access the following endpoints :

### addCountry (POST)
- endpoint - /api/country

### deleteCountry (DELETE)
- endpoint - /api/country/{id}

### updateCountry (PUT)
- endpoint - /api/country/{id}

### fetchSingleCountryById (GET)
- endpoint - /api/country/id/{id}

### fetchSingleCountryByName (GET)
- endpoint - /api/country/id/{name}

### fetchAllCountries (GET)
- endpoint - /api/country/all

### fetchCountriesSortedByProperties (GET)
- endpoint - /api/country/all/sortedBy/{property}

## Usage
API usage with detailed examples can be can be found at https://countryrestapi2ibi.herokuapp.com/swagger-ui.html under Country Resource

## License
[MIT](https://choosealicense.com/licenses/mit/)
