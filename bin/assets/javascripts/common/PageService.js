var PageService = function ($scope, $http ,$timeout) {
    $scope.queryAll = true;
    if($scope.isNotInit){
        $scope.queryAll = false;
    }
    if(!$scope.pageSizes){
        $scope.pageSizes = [10, 20, 30];
    }
    $scope.pagingOptions = {
        pageSizes:$scope.pageSizes,
        pageSize: $scope.pageSizes[0],
        currentPage: 1
    };
    $scope.pager = {
        filter: {
        }
    };
    $scope.originalData = {};
    $scope.originalFilterData = {};

    $scope.filterOptions = {
        filterText:{
            searchText1: "",
            searchText2: "",
            searchText3: ""
        },
        useExternalFilter: true
    };

    $scope.queryRule ={

    };
    $scope.totalServerItems = 0;

    $scope.setPagingData = function (data, page, pageSize) {
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };

    $scope.getFilterDataAsync = function(pageSize, page, filterText){
        if($scope.originalData){
            var data;
            if (!filterText.searchText1) {
                filterText.searchText1 = '';
            }
            if (!filterText.searchText2) {
                filterText.searchText2 = '';
            }
            if (!filterText.searchText3) {
                filterText.searchText3 = '';
            }
            data = $scope.originalData.filter(function (item) {
                return JSON.stringify(item).toLowerCase().indexOf(filterText.searchText1.toLowerCase()) != -1
                    & JSON.stringify(item).toLowerCase().indexOf(filterText.searchText2.toLowerCase()) != -1
                    & JSON.stringify(item).toLowerCase().indexOf(filterText.searchText3.toLowerCase()) != -1;
            });
            $scope.originalFilterData = data;
            $scope.setPagingData(data, page, pageSize);
        }
    };


    $scope.getPageDataAsync = function(pageSize, page){
        $scope.setPagingData($scope.originalFilterData, page, pageSize);
    };


    $scope.getQueryDataAsync = function(pageSize, page){
        $timeout(function(){
            $scope.pager.filter = $scope.queryRule.filterText;
            $http.post($scope.pageUrl, $scope.pager).success(function (largeLoad) {
                var dataValue;
                if(largeLoad.list){
                    dataValue=  largeLoad.list;
                }else{
                    dataValue=  largeLoad.value;
                }
                $scope.originalData = dataValue;
                $scope.originalFilterData = dataValue;


                $scope.setPagingData(dataValue, page, pageSize);
                $scope.viewFlag = false;
                $scope.alertFlag = false;
                if(dataValue.length === 0){
                    $scope.viewFlag = true;
                }
            }).error(function(error){
                console.error("Unable to get reconcile: " + error);
                $scope.viewFlag = false;
                $scope.error = error;
                $scope.alertFlag = true;
            });
        }, [100], []);
    };

    if ($scope.queryAll) {
        $scope.getQueryDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
    }

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPageDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }
    }, true);

    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getFilterDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.$watch('queryRule', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getQueryDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }
    }, true);

    $scope.gridOptions = {
        data: 'myData',
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        columnDefs: $scope.columnDefs
    };
};