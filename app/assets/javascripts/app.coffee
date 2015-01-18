
dependencies = [
    'ui.date',
    'ngRoute',
    'angularFileUpload',
    'ngGrid',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.services',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig'

]

app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
    .config ($routeProvider, $locationProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/faces/user/user.html'
            })
            .when('/user/findUserInfo', {
                templateUrl: '/assets/faces/user/user.html'
             })
            .when('/user/findMenuInfo', {
            templateUrl: '/assets/faces/menu/menu.html'
             })
            .when('/user/findOrderInfo', {
            templateUrl: '/assets/faces/order/order.html'
             })
           .when('/rule/findRule', {
            templateUrl: '/assets/faces/rule/rule.html'
            })
            .otherwise({redirectTo: '/'})
## 加上这句去掉URL里的#号
#        $locationProvider.html5Mode(true);


@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', ['angularFileUpload', 'ngGrid'])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])

