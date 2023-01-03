## Architecture

The entire application will be composed of 6 layers:

a. The Presentation Layer - This layer presents the user with graphical user interfaces of the stage of the application the user is in.

b. The Utilities Layer - this layer provides various utility classes to facilitate the functionality of the application.

c. The Execution Layer - this layer is responsible for performing the steps needed to fulfill the client's request.

d. The Results View Layer - this layer provides a subsystem responsible for performing selected analysis on data retrieved from the Business Data Object.

e. The Analysis Layer - this layer is responsible for performing the analysis selected by the user.

f. The Data Fetcher Layer - this layer is responsible for fetching the data needed to perform an analysis.

## Component Diagram

![D2](https://user-images.githubusercontent.com/33135632/210420795-0955660d-9f4e-4dba-b9a7-f32ecd35f3da.png)

## Layered Architectural Pattern

![Layered_Architecture_Pattern](https://user-images.githubusercontent.com/33135632/210422615-f25634fb-67a4-4378-8851-007786c29a6e.png)

## Design Patterns
1. **Strategy Design Pattern:** The Strategy design pattern is used in our software to address the selection of different analyses to be performed during the application’s run time based on the user selection.
  b. Location of Implementation: In Context.java and Analysis.java located in the analysisLayer package. These two classes form the
foundation of the Strategy pattern while the sub-classes of Analysis.java in the analysisLayer package are the strategies.
2. **Singleton Design Pattern:** The Singleton design pattern is used to ensure that certain classes have only 1 instance.
  b. Location of implementation: ExecutionProxy.java in ExecutionLayer package. AuthenticationServer.java in utilitiesLayer package. CountryCodeRepository.java in utilitiesLayer and AnalysisViewsRepository.java in resultsViewLayer.
3. **Proxy Design Pattern:** The Proxy design pattern is used to implement a proxy to act as a buffer between the application user interface and the ExecutionFacade class. It will receive a UserSelectionObject and a JPanel, then set up, create, and invoke the UserSelectionFacade.
  b. Location of implementation: ExecutionProxy.java in executionLayer package.
4. **Facade Design Pattern:** The Facade is the brain of our application. It will perform a series of steps while making calls to all necessary components involved in fulfilling the client’s user interface selection.
  b. Location of implementation: ExecutionFacade.java in executionLayer package.
5. **Factory (Method) Design Pattern:** The Factory (method) design pattern is used in handling the creation of corresponding Class Objects based on the client’s user interface selection.
  b. Location of Implementation: SubjectFactory.java and ViewerFactory.java in resultsViewLayer. AnalysisFactory.java in analysisLayer.
6. **Observer Design Pattern:** The Observer pattern is used to handle the graph viewers. The graph viewers are subscribing to a Subject that notifies the viewers once data has been received. Upon being notified, the viewers will then draw their respective graphs.
  b. Location of Implementation: Viewer.java and Subject.java in resultsViewLayer package. There are different viewer classes that extend Viewer.java.
