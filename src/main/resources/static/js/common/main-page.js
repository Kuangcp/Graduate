$(function(){
    $("[data-url]").on('click', function () {
        turn($(this).data('url'))
    });

});

/**
 * 绑定首页logo的点击事件
 * @param type
 */
function home(type){
    $(".layui-logo").click(function () {
        $("#main_page").attr('src',type+'/init')
    })
}

/**
 * 页面切换
 * @param target
 */
function turn(target) {
    var url = location.href.split('/')
    url = url[url.length-1]
    $("#main_page").attr('src',url+'/'+target)
}

