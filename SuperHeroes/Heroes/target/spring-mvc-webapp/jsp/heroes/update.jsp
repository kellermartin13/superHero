<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Organization</title>
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
                <h1><span><img src="${pageContext.request.contextPath}/images/lightning-bolt-crop.jpg" style="width: 150px; height: auto"></span>uper 
                    Heroes
                </h1>
            </div>
            <div class="container">
                <h1>Update Hero</h1>
                <hr/>
                <h3>Update ${hero.name}</h3>
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="hero" 
                     action="${pageContext.request.contextPath}/heroes/update" method="POST">
                <div class="form-group">
                    <label for="editName" class="col-md-4 control-label">
                        Hero Name:
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="editName"
                                  path="name" placeholder="Hero Name"/>
                        <sf:errors path="name" cssclass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="editDescription" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" 
                                  id="editDescription" path="description" 
                                  placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                        <sf:hidden path="heroId"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Super"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

