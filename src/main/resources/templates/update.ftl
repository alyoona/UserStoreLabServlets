<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <!--my custom styles:-->
    <link rel="stylesheet" href="/assets/css/custom.css">

    <title>Update User</title>
</head>
<body>

<nav class="sticky-top" aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/users">Home</a></li>
        <li class="breadcrumb-item"><a href="/users/add">Add User</a></li>
    </ol>
</nav>

<div class="container">
    <div class="row mt-0">

        <div class="adding-form col-md-6 offset-md-3 pb-5 pl-5 pr-5">
        <!--div class="bg-light col-md-6 offset-md-3 pt-1 pb-5 pl-5 pr-5 rounded"-->

            <h5 class="title font-weight-light">Change user here</h5>
            <!--h1 class="font-weight-light text-center pt-3 pb-3"><b>Change</b> the user here</h1-->

            <form action="/users/update" method="POST">
                <input type="hidden" name="id" id="id" value=${user.id}>
                <div class="form-group">
                    <label for="name" class="font-weight-light">Name:</label>
                    <input type="text" name="name" class="form-control" id="name" value="${user.name}">
                    <small id="namez" class="form-text text-muted">Update user name</small>
                </div>
                <div class="form-group">
                    <label for="salary" class="font-weight-light">Salary:</label>
                    <input type="number" step="0.01" name="salary" class="form-control"
                           id="salary" value=${user.salary?c}>
                    <small id="salaryz" class="form-text text-muted">Update user salary</small>
                </div>
                <div class="form-group">
                    <label for="dateOfBirth" class="font-weight-light">Date of Birth:</label>
                    <input type="date" name="dateOfBirth" class="form-control"
                           id="dateOfBirth" value=${user.dateOfBirth}>
                    <small id="dateOfBirthz" class="form-text text-muted">Update user birthday</small>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Save changes</button>

            </form>
        </div>
    </div>
    <div class="row mt-0">
        <div class="col-md-6 offset-md-3 p-0">
            <div class="status-message rounded pb-3 text-center">
                <h4 class="font-weight-light ">
                    <#if updatedUser??>User was changed to user with name "${user.name}", salary "${user.salary?string["0.00"]}" and birthday "${user.dateOfBirth}".</#if>
                </h4>
            </div>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>
</html>