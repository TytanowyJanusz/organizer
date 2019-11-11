app.service('mainService',[
    '$http', function mainService($http){

    var service = this;
    var url = 'http://localhost:8081/organizerApi';
    var tasks;
    this.getTasksForUser = function (userId)
    {
        console.log('work work');
        $http({
            method: 'GET',
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Access-Control-Request-Method': 'GET'

             },
            url: url+'/tasksForUser',
            params: {userId: userId}
        }).then(function successCallback(response) {
            tasks = response.data;
            return tasks;
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });
    }
        }
    ]);
