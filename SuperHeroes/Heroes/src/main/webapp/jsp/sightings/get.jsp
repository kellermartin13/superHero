<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/heroes.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/heroes.css" rel="stylesheet">  
        <link href="https://fonts.googleapis.com/css?family=Knewave|Oswald" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" type="image/x-icon" />
    </head>
    <body>
        <div class="container col-md-12">
            <div class="container">
                <nav class="dropdownmenu col-md-12 col-md-offset-1">
                    <ul>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/heroes">Heroes</a></li>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/orgs">Organizations</a></li>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                        <li class="col-sm-6 col-md-2"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    </ul>    
                </nav>
            </div>
            <div class="logo">
                <h1><span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>uper 
                    <span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>ightings
                </h1>
            </div>
            <div class="container col-md-8 col-md-offset-2">
                <form action="${pageContext.request.contextPath}/sightings/getByHero">
                    <div class="form-group col-md-4">
                        <label for="heroes">Select Hero:</label>
                        <select class="form-control" name="heroId">
                            <c:forEach var="hero" items="${heroes}">
                                <option value="${hero.heroId}">${hero.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" class="btn btn-default" value="Search By Hero">
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/sightings/getByLocation">
                    <div class="form-group col-md-4">
                        <label for="locations">Select Location:</label>
                        <select class="form-control" name="locationId">
                            <c:forEach var="location" items="${locations}">
                                <option value="${location.locationId}">${location.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" class="btn btn-default" value="Search By Location">
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/sightings/getByDate">
                    <div class="form-group col-md-4">
                        <label for="date">Select Date:</label>
                        <input type="date" name="date" class="form-control">
                        <input type="submit" class="btn btn-default" value="Search By Date">
                    </div>
                </form> 
            </div>
            <div class="outputList col-md-8 col-md-offset-2">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Sighting Date</th> 
                            <th>Heroes</th>
                            <th>Location</th>
                            <th>Edit/Delete</th>
                        </tr>
                    </thead>
                    <c:forEach var="sighting" items="${sightings}">
                        <tbody>
                            <tr>
                                <td>
                                    <c:out value="${sighting.date}"/>
                                </td>
                                <td>
                                    <c:forEach var="hero" items="${sighting.heroes}">
                                        <c:out value="${hero.name}"/>
                                    </c:forEach>

                                </td>
                                <td>
                                    <c:out value="${sighting.location.name}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/sightings/update/${sighting.sightingId}">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    /
                                    <a href="${pageContext.request.contextPath}/sightings/delete/${sighting.sightingId}">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach>

                </table>
            </div>        
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

