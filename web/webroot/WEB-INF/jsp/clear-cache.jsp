<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><spring:message code="welcome.title"
		text="Hybris training" /></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/training.server.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/animate.css">

</head>
<body data-ng-app="formSubmit">

	<script type="text/javascript">
		var ctx = '<%=request.getContextPath()%>';
	</script>

	<script
		src="${pageContext.request.contextPath}/js/lib/jquery-1.12.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/lib/angular.min.1.3.8.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/lib/angular-route.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/lib/angular-resource.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/app/clear-cache.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#cacheModel').each(function() {
				animationClick(this, 'fadeOut');
			});
		});
	</script>


	<h2>Clear cache</h2>

	<div style="width: 70%; background-color: white;" id="boxshadow" data-ng-init="getAllCacheRegions()" data-ng-controller="CacheRegionInitController">
		<form data-ng-submit="submit()"
			data-ng-controller="FormSubmitController">
			<h3>{{headerText}}</h3>
			<p>
				<select ng-model="selectedRegion">
				  <option value="all">All</option>
		        <option ng-repeat="reg in regions" value="{{reg}}">{{reg}}</option>
		      </select>
			</p>
			<input id="cache" type="image" value="Submit"
				src="${pageContext.request.contextPath}/img/clear-button.png"
				width="6%" height="6%" ng-class="style" /><br>
			
			<pre>{{done}}</pre>
		</form>
	</div>

	<h2>Invalidate item</h2>

	<div style="width: 70%; background-color: white;" id="boxshadow">
		<form data-ng-submit="submit()"
			data-ng-controller="FormSubmitControllerInvalidate">
			<h3>{{headerText}}</h3>
				<p>Product:    <input type="text" data-ng-model="item"></p>
				<p>Media:    <input type="text" data-ng-model="media"></p>
			<input id="cache" type="image" value="Submit"
				src="${pageContext.request.contextPath}/img/clear-button.png"
				width="6%" height="6%" ng-class="style" /><br>
			
			<pre>{{done}}</pre>
		</form>
	</div>

</body>
</html>