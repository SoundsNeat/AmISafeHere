Overview:

This is a project for CS-480: Software Engineering class by SoundsNeat team.
======================

Goal:

The goal is to calculate the safety level of any city within United States.
======================

Name:

Am I Safe Here?
www.amisafehere.xyz
======================

Technologies:

1. Java Spring as Web Server (Backend Engine)
2. Bootstrap's CSS and JS library as response front end design
3. Angular JS to communicate to server
3. Template from StartBootstrap.com for UI
4. Teleport JavaScript from MIT for better UX at getting city, state, country
5. Plotly JavaScript graphs to display statistics as a better UI
6. JSON format to transfer data
7. MAVEN as project management and comprehension tool
8. Amazon EC2 as a server
9. Jenkins for easier continuous integration.
10. Git as a Source Control
11. HTML5 Geolocation to get coordinates
12. Google API to convert coordinates to address
13. Google Map API to display the selected city.
14. JUnit as Unit Tests
15. http://www.city-data.com/ to get the crime statistics for selected cities
======================

Constraints:

1. Unable to use GoogleChrome for auto detection.
    Chrome does not allow geolocation for websites without SSL.
    Hence it is not supported.
    => Possible Fix: add SSL certificate
2. No data for some cities.
    Since this project uses city-data.com, some of the cities which are not in that website
    are impossible to calculate.
    => Possible Fix: used a more reliable data possible connected to police stations.
3. Different naming conventions between Teleport JS and city-data.com
    Since they are 2 different source, some of the cities have different naming conventions
    ex: New York City vs New York.
    => Fix: Have a running list of different cities objects to replace with the correct name.
            Currently, it is a static List which needs to be redeployed whenever it is updated.
            It can be improved by reading from a file.
======================

Future Goals:

1. Update to a more reliable data source.
2. Use locality such as neighborhood as safety level of a city is a generalization when cities are too big.
3. Add comparison feature to compare against other cities.
4. Create a database and populate the data every month.
======================


