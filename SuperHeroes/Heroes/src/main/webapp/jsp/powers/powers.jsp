<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Powers</title>
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
                <h1><span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>uper Powers
            </div>
            <div class="container">
                <h3>Woah! There are some crazy powers out there!</h3>
            </div>

            <div class="container options">
                <div class="addBox">
                    <h3>Add A New Power</h3>
                    <p>Do you possess a revolutionary new power? Have you 
                        lain witness to the first recorded sighting of 
                        _____? Let your fellow man know!</p>
                    <a href="${pageContext.request.contextPath}/powers/add">
                        <button class="btn btn-default" id="addBtn">Add Power</button>
                    </a>
                </div>
                <hr />
                <div class="allBox">
                    <h3>Check Out All The Powers</h3>
                    <p>Here's all the recorded super powers. Check them out!</p>
                    <a href="${pageContext.request.contextPath}/powers/getAll">
                        <button class="btn btn-default" id="addBtn">Get Powers</button>
                    </a>
                </div>
                <!--                <hr />
                                <div class="updateBox">
                                    <h3>Update Power Information</h3>
                                    <p>Did your power get cooler or lamer? Even if you lean 
                                        toward villainy, I hope its cooler.</p>
                                    <a href="${pageContext.request.contextPath}/powers/update">
                                        <button class="btn btn-default" id="addBtn">Update Power</button>
                                    </a>
                                </div>
                                <hr />
                                <div class="deleteBox">
                                    <h3>Delete a Power</h3>
                                    <p>Delete an entire power? What in the world would 
                                        make that remotely necessary?</p>
                                    <a href="${pageContext.request.contextPath}/powers/delete">
                                        <button class="btn btn-default" id="addBtn">Delete Power</button>
                                    </a>
                                </div>-->
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

