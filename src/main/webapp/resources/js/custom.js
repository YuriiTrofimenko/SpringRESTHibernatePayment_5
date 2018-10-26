var preloaderHide = function () {
    $('.preloader-wrapper').css('display', 'none');
}

var preloaderShow = function () {
    $('.preloader-wrapper').css('display', 'block');
}

//
var onSignIn = function (accountInfo) {

    $("a[href='#!home:out']").text('Signout (' + accountInfo.userName + ')');
    $("a[href='#!home:out']").css('display', 'block');

    $("a[href='#!signin']").css('display', 'none');
    $("a[href='#!signup']").css('display', 'none');
    
    $("a[href='#!cart']").css('display', 'block');
}

var onSignOut = function () {

    $("a[href='#!home:out']").text('');
    $("a[href='#!home:out']").css('display', 'none');

    $("a[href='#!signin']").css('display', 'block');
    $("a[href='#!signup']").css('display', 'block');
    
    $("a[href='#!cart']").css('display', 'none');

    $("#admin").html('');
}

$(document).ready(function () {

    $('.sidenav').sidenav();
    //$('.modal').modal();
    /*$.get("/api/auth/checkauth")
        .done(function (resp) {

            if (resp != null) {

                onSignIn(resp);
            }
        })
        .fail(function () { alert("Fatal error"); });*/
});