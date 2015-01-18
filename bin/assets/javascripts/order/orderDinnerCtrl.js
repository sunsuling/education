var OrderDinnerCtrl = function ($modal,$cacheFactory, $scope, $http, $timeout, $location, OrderService) {

    $scope.orderInfo = {
        count:0,
        orgPrice : 0,
        salePrice : 0,
        showCart : false,
        showList :true,
        showLogin : false,
        myOrder : false,
        foods : [],
        userName: "",
        loginFlg : false,
        orgFlg : false,
        alertFlag:false,
        error:""
    };
    var localData = localStorage.getItem("orderInfo");
    if(localData){
        $scope.orderInfo =  JSON.parse(localData);
    }

    $scope.pageUrl = "/findFood";
    new PageService($scope, $http, $timeout);

    $scope.$watch('orderInfo', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            var objStr=JSON.stringify($scope.orderInfo);
            localStorage.setItem("orderInfo",objStr);
        }
    }, true);

    OrderDinnerCtrl.prototype.myOrder = function () {
        return OrderService.myOrder().then((function (data) {
            $scope.orderInfo.showLogin = false;
            $scope.orderInfo.showCart = false;
            $scope.orderInfo.showList = false;
            $scope.orderInfo.myOrder = true;
            $scope.orderInfo.orders = data.data.list;
        }), function (error) {
            console.error("find myOrderList error: " + error.data);
            showError(error);
        });
    };

    OrderDinnerCtrl.prototype.showOption = function (order) {
        if(order.canBeToDel === "Y"){
            return true;
        }
        return false;
    };

    OrderDinnerCtrl.prototype.tologin = function () {
        $scope.orderInfo.orgFlg = $scope.orderInfo.showList;
        $scope.orderInfo.showCart = false;
        $scope.orderInfo.showList = false;
        $scope.orderInfo.myOrder = false;
        $scope.orderInfo.showLogin = true;
    };

    OrderDinnerCtrl.prototype.register = function () {
        console.debug("register()");
        if($scope.userInfo.password != $scope.userInfo.passwordConfirm){
            $scope.errormsg = "两次输入密码不一致！";
            return;
        }

        return OrderService.register($scope.userInfo).then((function (data) {
            $scope.orderInfo.userName = $scope.userInfo.userName;
            $scope.orderInfo.loginFlg = true;
            $scope.orderInfo.showLogin = false;
            $scope.orderInfo.showCart = true;
            $scope.orderInfo.showList = false;
            if($scope.orderInfo.orgFlg){
                $scope.orderInfo.showCart = false;
                $scope.orderInfo.showList = true;
            }
        }), function (error) {
            $scope.orderInfo.loginFlg = false;
            console.error("register error: " + error.data);
            $scope.errormsg = error.data;
        });
    };

    OrderDinnerCtrl.prototype.login = function () {
        console.debug("login()");
        return OrderService.login($scope.user).then((function (data) {
            $scope.orderInfo.userName = data.data;
            $scope.orderInfo.loginFlg = true;
            $scope.orderInfo.showLogin = false;
            $scope.orderInfo.showCart = true;
            $scope.orderInfo.showList = false;
            $scope.orderInfo.myOrder = false;
            if($scope.orderInfo.orgFlg){
                $scope.orderInfo.showCart = false;
                $scope.orderInfo.showList = true;
                $scope.orderInfo.myOrder = false;
            }

        }), function (error) {
            $scope.orderInfo.loginFlg = false;
            $scope.orderInfo.userName = "";
            console.error("Unable to get activities: " + error);
            showError(error);
        });
    };

    OrderDinnerCtrl.prototype.logout = function () {
        console.debug("logout()");
        $scope.orderInfo.userName = "";
        $scope.orderInfo.loginFlg = false;
    };



    OrderDinnerCtrl.prototype.addFood = function (food) {
        var foodTemp = {
            id: food.id,
            foodId: food.foodId,
            foodName: food.foodName,
            source: food.source,
            priceStr: food.priceStr,
            price: food.price,
            count: food.count,
            picture: food.picture
        };

        $scope.orderInfo.foods.push(foodTemp);
        $scope.orderInfo.count++;
        $scope.orderInfo.orgPrice += food.price;
        $scope.orderInfo.salePrice += food.price;
    };

    OrderDinnerCtrl.prototype.deleteFood = function (food) {
        if (confirm('您确实要把该商品移出购物车吗？')) {
            $scope.orderInfo.foods.splice(food.rowIndex, 1);
            $scope.orderInfo.count--;
            $scope.orderInfo.orgPrice -= food.price;
            $scope.orderInfo.salePrice -= food.price;
        }
    };

    OrderDinnerCtrl.prototype.clearFoods = function () {
        if (confirm('您确实要清空购物车吗？')) {
            $scope.orderInfo.count = 0;
            $scope.orderInfo.foods = [];
            $scope.orderInfo.orgPrice = 0;
            $scope.orderInfo.salePrice = 0;
        }
    };

    OrderDinnerCtrl.prototype.confirmOrder = function () {
        if(!$scope.orderInfo.loginFlg){
            $scope.orderInfo.showCart = false;
            $scope.orderInfo.showList = false;
            $scope.orderInfo.showLogin = true;
            $scope.orderInfo.orgFlg = false;
            return;
        }

        if (confirm('您确实要提交订单吗？')) {
            $scope.pager.list = $scope.orderInfo.foods;
            OrderService.add($scope.pager).then(function (data) {
                console.info("confirm order successfully : " + data);
                $scope.orderInfo.count = 0;
                $scope.orderInfo.foods = [];
                $scope.orderInfo.orgPrice = 0;
                $scope.orderInfo.salePrice = 0;
                alert("订餐成功！请查看右上角我的订单确认！");
            }, function (error) {
                showError(error);
                console.error("confirm order error : " + error.data);
                alert("订餐失败！请重新提交！");
            });


        }
    };

    OrderDinnerCtrl.prototype.showCart = function () {
        $scope.orderInfo.showCart = true;
        $scope.orderInfo.showList = false;
        $scope.orderInfo.showLogin = false;
        $scope.orderInfo.myOrder = false;
    };

    OrderDinnerCtrl.prototype.hideCart = function () {
        $scope.orderInfo.showCart = false;
        $scope.orderInfo.showList = true;
        $scope.orderInfo.showLogin = false;
        $scope.orderInfo.myOrder = false;
    };



    OrderDinnerCtrl.prototype.findResult = function () {
        console.debug("findResult()");
        return OrderService.findResult($scope.pager).then((function (data) {
            console.debug("Promise returned " + data.length + " banks");
            $scope.pager = data.data;
            $scope.orderInfo.results = data.data.list;
        }), function (error) {
            showError(error);
            console.error("Unable to get activities: " + error);
            $scope.orderInfo.error = error;
        });
    };

    OrderDinnerCtrl.prototype.deleteOrder = function (row) {
        OrderService.delete(row).then(function (data) {
            console.info("delete to userInfo successfully : " + data);
            $scope.orderInfo.orders.splice(row.rowIndex,1);
        }, function (error) {
            showError(error);
            console.error("delete to userInfo error : " + error.data);
        });
    };
    $scope.removeAlert = function () {
        $scope.alertFlag = false;
    };
    function showError(error){
        $scope.orderInfo.error = error.data;
        $scope.alertFlag = true;
        $timeout(function(){
            $scope.alertFlag = false;
        }, [5000], []);
        if($scope.orderInfo.error === "登录超时，请重新登录！"){
            $scope.orderInfo.userName = "";
            $scope.orderInfo.loginFlg = false;
        }
    }
};

controllersModule.controller('OrderDinnerCtrl', OrderDinnerCtrl);