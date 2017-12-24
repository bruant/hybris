var app = angular.module('formSubmit', []);

app.controller('FormSubmitController',[ '$scope', '$http', function($scope, $http) {

	$scope.headerText = 'Search for product';
	$scope.submit = function() {

		var formData = {
				"code" : $scope.code,
				"catalog" : $scope.catalog,
				"version" : $scope.version
		};

		 setTimeout(function ()
				   {
				     $scope.$apply(function()
				     {
				 		$scope.style='animated fadeOut';
				 		$scope.list = [];
				     });
				   }, 500);

		var response = $http.post(ctx + '/search', formData);
		response.success(function(data, status, headers, config) {

			 setTimeout(function ()
					   {
					     $scope.$apply(function()
					     {
								$scope.list.push(data);
								$scope.style='animated fadeIn';
					     });
					   }, 1000);

		});
		response.error(function(data, status, headers, config) {
			alert( "Exception details: " + JSON.stringify({data: data}));
		});

		//Empty list data after process
		$scope.list = [];

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

