var app = angular.module('formSubmit', []);

app.controller('FormSubmitController',[ '$scope', '$http', function($scope, $http) {

	$scope.headerText = 'Choose cache from the list to clear:';
	
	$scope.submit = function() {

		var formData = {
				"region" : $scope.region,
				"selectedRegion" : $scope.selectedRegion
		};

		 setTimeout(function ()
				   {
				     $scope.$apply(function()
				     {
				 		$scope.style='animated fadeOut';
				 		$scope.list = [];
				     });
				   }, 500);

		var response = $http.post(ctx + '/cache', formData);
		response.success(function(data, status, headers, config) {

			 setTimeout(function ()
					   {
					     $scope.$apply(function()
					     {
					    	 	$scope.done = 'Chache clear is done.';
								$scope.style='animated fadeIn';
					     });
					   }, 1000);

		});
		response.error(function(data, status, headers, config) {
			alert( "Exception details: " + JSON.stringify({data: data}));
		});

		//Empty list data after process
		$scope.done = '';

	};
}]);

app.controller('CacheRegionInitController',[ '$scope', '$http', function($scope, $http) {

	$scope.headerText = 'Clear cache';
	$scope.getAllCacheRegions = function() {
		$http({method: 'GET', url: '/training/getAllCacheRegions'}).
		success(function(data, status, headers, config) {
        	$scope.regions = data;	        	
        }).
        error(function(data, status, headers, config) {
        	alert( "Exception occurred: " + JSON.stringify({data: data}));
        });		   
	};
	
}]);

app.controller('FormSubmitControllerInvalidate',[ '$scope', '$http', function($scope, $http) {

	$scope.headerText = 'Choose item  type from the list to clear:';
	
	$scope.submit = function() {

		var formData = {
				"item" : $scope.item,
				"media" : $scope.media
		};

		 setTimeout(function ()
				   {
				     $scope.$apply(function()
				     {
				 		$scope.style='animated fadeOut';
				 		$scope.list = [];
				     });
				   }, 500);

		var response = $http.post(ctx + '/cache/key', formData);
		response.success(function(data, status, headers, config) {

			 setTimeout(function ()
					   {
					     $scope.$apply(function()
					     {
					    	 	$scope.done = 'Chache clear is done.';
								$scope.style='animated fadeIn';
					     });
					   }, 1000);

		});
		response.error(function(data, status, headers, config) {
			alert( "Exception details: " + JSON.stringify({data: data}));
		});

		$scope.done = '';

	};
}]);


function animationClick(element, animation){
    element = $(element);
    element.click(
        function() {
            element.addClass('animated ' + animation);
            //wait for animation to finish before removing classes
            window.setTimeout( function(){
                element.removeClass('animated ' + animation);
            }, 2000);

        });
}

