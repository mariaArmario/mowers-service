# Mowers Service
This application provides an algorithm that calculate the mower final position in a green grass plateau based on it initial position and on a list of instructions. We assume that the limit is at coordinates (0,0) to positive X, Y

##To initialite and create dependencies
- make build

##To see database info
- go to http://localhost:8080/h2-console
- introduce:
    - JDBC URL -> jdbc:h2:mem:mowersdb
    - User Name -> sa
    - Password -> password

##To test application
- curl is:
    - curl --location --request GET 'http://localhost:8080/mowers/location?position=:position&movement=:movement'
- :position is a required query param where introduce the initial position of mower (only three characters)
    - example: 12N
- :movement is a required query param where introduce a list of instructions ()
    - example: LMLMLMLMM
- if params are correct, the response will be final position with three characters.
    -example: 13N

###Posible response
- if the third element of position is not N, S, W, E -> BadRequest
- if any element of movement is different from L, R, M -> BadRequest
- if X or Y coordinates in the final position are negative -> BadRquest (out of plateau)
