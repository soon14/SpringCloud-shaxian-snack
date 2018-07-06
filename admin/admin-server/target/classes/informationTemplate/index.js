var serverUrl = "http://api.isxxc.com/home";
var pageviewUrl = serverUrl + "/informationInfo/pageview";
var pageviewIncrUrl = serverUrl + "/informationInfo/pageviewIncr";
$(document).ready(function () {

    setTimeout(function () {
        var message = document.body.scrollHeight;
        window.parent.postMessage(message, "*");
    }, 700);

    var sourceName = $("#sourceName").val();

    //Cookice操作，当前文章查看记录
    var pageViewKey = "pageView_" + sourceName;
    var pageViewValue = $.cookie(pageViewKey);
    if (!pageViewValue) {
        //请求添加阅读记录
        $.post(pageviewIncrUrl, {
            sourceName: sourceName
        }, function (data) {
            if (data.code == 200) {
                //添加当前文章访问记录到Cookice
                $.cookie(pageViewKey, true, {
                    expires: 180
                });
            }
        }, "json");
    }
    //获取阅读数
    $.get(pageviewUrl, {
        sourceName: sourceName
    }, function (data) {
        $("#pageView").html(data);
    });
});