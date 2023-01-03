## Archictecture
The entire application will be composed of 6 layers
a. The Presentation Layer - This layer presents the user with graphical user interfaces of the stage of the application the user is in. If a user has not been granted access to the application, they will be presented with a login or register graphical user interface and if they have been granted access, they will be presented with the main application graphical user interface.
b. The Utilities Layer - this layer provides various utility classes such as AuthenticationServer, User Selection Business Data Object, Repositories, and Validators to facilitate the functionality of the application.
c. The Results View Layer - this layer provides a subsystem responsible for performing selected analysis on data retrieved from the Business Data Object.
d. The Execution Layer - this layer is responsible for performing the steps needed to fulfill the client's request.
e. The Analysis Layer - this layer is responsible for performing the analysis selected by the user.
f. The Data Fetcher Layer - this layer is responsible for fetching the data needed to perform an analysis.

