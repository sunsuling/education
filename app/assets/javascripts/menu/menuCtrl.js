var MenuInfoCtrl = function ($modal, $scope, $location, MenuService, FileUploader, $http, $timeout) {

    // 每页条数 及条数可选项(默认为[10,20,30])
    //$scope.pageSizes = [3, 4, 5];
    // 不需要页面载入直接展示数据的设置为true,默认为展示
    //$scope.isNotInit = true;
    $scope.columnDefs = [
        {field: 'foodId', displayName: '菜单ID'},
        {field: 'foodName', displayName: '菜名'},
        {field: 'priceStr', displayName: '价格'},
        {field: 'foodType', displayName: '类别'},
        {field: 'source', displayName: '来源'},
        {field: 'locked', displayName: '操作', cellTemplate: '/assets/faces/cell/userCell.html'}
    ];
    $scope.pageUrl = "/findMenuInfo";

    new PageService($scope, $http, $timeout);

    $scope.addRow = function () {
        var row = {
            entity:{
            }
        };
        $scope.open(row);
    };
    $scope.editRow = function (row) {
        $scope.open(row);
    };
    $scope.deleteRow = function (row) {
        MenuService.delete(row.entity).then(function (data) {
            console.info("delete to menuInfo successfully : " + data);
            $scope.myData.splice(row.rowIndex,1);
        }, function (error) {
            console.error("delete to menuInfo error : " + error.data);
        });
    };
    $scope.open = function (row) {
        $modal.open({
            templateUrl: "MenuModalContent.html",
            controller: "MenuModalCtrl",
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

controllersModule.controller('MenuInfoCtrl', MenuInfoCtrl);