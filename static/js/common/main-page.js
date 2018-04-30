/**
 * 绑定首页logo的点击事件
 * @param type
 */
function home(type) {
  $(".layui-logo").click(function () {
    $("#main_page").attr('src', '/' + type + '/init.html')
  })
}

/**
 * 页面切换
 * @param target
 */
function turn(role, target) {
  checkRoleThen(role, function () {
    $("#main_page").attr('src', location.href + '/' + target + '.html')
  })

}

