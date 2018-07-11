<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Hero</title>
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
            <h1>Add Hero</h1>
            <div class="container">
                <form action="${pageContext.request.contextPath}/heroes/confirmAdd" method="POST">
                    <div class="form-group col-md-offset-4 col-md-4">

                        <label for="orgName">Hero Name:</label>
                        <br/>
                        <input type="text" class="form-control" 
                               name="heroName" id="heroName" 
                               placeholder="New Hero Here"/>

                        <label for="description">Short Description:</label><br/>
                        <input type="text-area" class="form-control" 
                               name="description" id="description" 
                               placeholder="Tell us everything!"/>

                        <div class="form-group">
                            <label for="power">What powers does your hero wield?*</label>
                            <br/>
                            <select multiple class="form-control" name="powerid">
                                <c:forEach var="power" items="${powers}">
                                    <option value="${power.powerId}">${power.name}</option>
                                </c:forEach>
                            </select>   
                            <br/>
                        </div>
                        <div class="form-group">
                            <label for="org">Add to Known Organizations*:</label>
                            <br/>
                            <select multiple class="form-control" name="orgId">
                                <c:forEach var="org" items="${orgs}">
                                    <option value="${org.orgId}">${org.name}</option>
                                </c:forEach>
                            </select>
                            <br/>
                        </div>
                        <p style="font-size: small">*Hold down the Ctrl 
                            (windows) / Command (Mac) 
                            button to select multiple options.</p>
                        <button type="submit" class="btn btn-default">Add Hero</button>
                    </div>
                </form>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

