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
            <div class="container options">
                <div class="addBox">
                    <h3>Add A New Organization</h3>
                    <p>Are you trying to start the next cool Hero Club? We want 
                        to know about it. In fact, there is no better way to 
                        advertise your new Hero Hub.</p>
                    <a href="${pageContext.request.contextPath}/orgs/add">
                        <button class="btn btn-default" id="addBtn">
                            Add Organization</button>
                    </a>
                </div>
                <hr />
                <div class="allBox">
                    <h3>Check Out All The Organizations</h3>
                    <p>Looking to network with some different Supers? 
                        Maybe you want to spy on the competition? 
                        Click here for a full list of Super Orgs.</p>
                    <a href="${pageContext.request.contextPath}/orgs/getAll">
                        <button class="btn btn-default" id="addBtn">
                            See All Organizations</button>
                    </a>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

