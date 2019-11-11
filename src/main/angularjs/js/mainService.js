app.service('mainService',[
    '$http', function mainService($http){
    console.log('zyje');
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
                'Accept':'application/hal+json, text/html;charset=utf-8'
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
