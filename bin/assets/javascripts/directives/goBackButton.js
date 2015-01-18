(function () {
    directivesModule.directive('backButton', function () {
        return {
            restrict: 'A',
            link: function (scope, element) {
                element.bind('click', goBack);
                function goBack() {
                    history.back();
                    scope.$apply();
                }
            }
        };
    });
}).call(this);