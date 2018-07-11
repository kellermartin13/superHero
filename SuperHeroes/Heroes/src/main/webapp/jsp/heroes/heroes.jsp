<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heroes</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
                <h1><span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>uper Heroes
                </h1>
            </div>
            <div class="container options">
                <div class="addBox">
                    <h3>Add A New Hero</h3>
                    <p>Do you have a new Hero to add? How exciting! 
                        We are going to need a little bit of info...</p>
                    <a href="${pageContext.request.contextPath}/heroes/add">
                        <button class="btn btn-default" id="addBtn">
                            Add Hero</button>
                    </a>
                </div>
                <hr />
                <div class="allBox">
                    <h3>Check Out All The Known Heroes</h3>
                    <p>Want to see a list of all the heroes in our system? Or 
                        maybe you're just checking to see if you made the cut. 
                        I see you, Deadpool.</p>
                    <a href="${pageContext.request.contextPath}/heroes/getAll">
                        <button class="btn btn-default" id="addBtn">
                            See All Heroes</button>
                    </a>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

