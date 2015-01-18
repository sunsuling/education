var UserInfoCtrl = function ($modal, $scope, $location, UserService, FileUploader, $http, $timeout) {

    // 每页条数 及条数可选项(默认为[10,20,30])
    //$scope.pageSizes = [3, 4, 5];
    // 不需要页面载入直接展示数据的设置为true,默认为展示
    //$scope.isNotInit = true;
    $scope.columnDefs = [
        {field: 'userId', displayName: '用户ID'},
        {field: 'userName', displayName: '用户名'},
        {field: 'email', displayName: '邮箱'},
        {field: 'locked', displayName: '操作', cellTemplate: '/assets/faces/cell/userCell.html'}
    ];
    $scope.pageUrl = "/findUserInfo";

    new PageService($scope, $http, $timeout);

    $scope.addRow = function () {
        var row = {
            entity:{
                userName:"",
                email:""
            }
        };
        $scope.open(row);
    };
    $scope.editRow = function (row) {
        $scope.open(row);
    };
    $scope.deleteRow = function (row) {
        UserService.delete(row.entity).then(function (data) {
            console.info("delete to userInfo successfully : " + data);
            $scope.myData.splice(row.rowIndex,1);
        }, function (error) {
            console.error("delete to userInfo error : " + error.data);
        });
    };
    $scope.open = function (row) {
        $modal.open({
            templateUrl: "UserModalContent.html",
            controller: "UserModalCtrl",
            resolve: {
                row: function(){
                    return row;
                },
                myData: function(){
                   return  $scope.myData;
                }
            }
        });
    };

    $scope.removeAlert = function () {
        $scope.alertFlag = false;
    };
};

controllersModule.controller('UserInfoCtrl', UserInfoCtrl);