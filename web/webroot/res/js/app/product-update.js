var app = angular.module('formSubmit', []);

app.controller('FormSubmitController',[ '$scope', '$http', function($scope, $http) {

	$scope.headerText = 'Give a list of products and manufacturer name';
	
	$scope.submit = function() {

		var formData = {
				"products" : $scope.products,
				"manufacturer" : $scope.manufacturer
		};

		 setTimeout(function ()
				   {
				     $scope.$apply(function()
				     {
				 		$scope.style='animated fadeOut';
				 		$scope.result = '';
				     });
				   }, 500);

		var response = $http.post(ctx + '/update', formData);
		response.success(function(data, status, headers, config) {

			 setTimeout(function ()
					   {
					     $scope.$apply(function()
					     {
					    	 	$scope.result.push = data.result;
								$scope.style='animated fadeIn';
					     });
					   }, 1000);

		});
		response.error(function(data, status, headers, config) {
			alert( "Exception details: " + JSON.stringify({data: data}));
		});

		$scope.result = '';

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

