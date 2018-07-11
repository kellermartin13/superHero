<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Organizations</title>
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
                <h1><span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>uper Organizations
            </div>
            <h3>All the Super Organizations in the World! 
                (That we know of...)</h3>
            <div class="outputList">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Organization</th> 
                            <th>Description</th>
                            <th>Members</th>
                            <th>Location</th>
                            <th>City</th>
                            <th>Country</th>
                            <th>Edit/Delete</th>
                        </tr>
                    </thead>
                    <c:forEach var="org" items="${orgs}">
                        <tbody>
                            <tr>
                                <td>
                                    <c:out value="${org.name}"/>
                                </td>
                                <td>
                                    <c:out value="${org.description}"/>
                                </td>
                                <td>
                                    <c:forEach var="hero" items="${org.heroes}">
                                        <c:out value="${hero.name}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${org.location.name}"/>
                                </td>
                                <td>
                                    <c:out value="${org.location.city}"/>
                                </td>
                                <td>
                                    <c:out value="${org.location.country}"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/orgs/update/${org.orgId}">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </a>
                                    /
                                    <a href="${pageContext.request.contextPath}/orgs/delete/${org.orgId}">
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

