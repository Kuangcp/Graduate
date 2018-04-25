/**
 * 登录弹窗
 * @param role 角色
 */
function login(role) {
  layer.prompt({
    formType: 0,
    value: '2',
    title: '请输入用户名'
  }, function (value1, index, elem) {
    layer.close(index);
    layer.prompt({
      formType: 1,
      value: '2',
      title: '请输入密码'
    }, function (value2, index, elem) {
      layer.close(index);
      var data = {
        username : value1,
        password : value2,
        role : role
      };
      data = JSON.stringify(data);
      // console.log('发送消息：  '+data)
      $.post(host + '/login', data, function (data) {
        console.log(data);
        if (data.code === '0' ) {
          sessionStorage.setItem(mythos, data.data);
          layer.msg('登录成功 ')
        }else{
          login(role);
          layer.msg('用户名或密码错误')
        }
      }, "json");
    });
  });
}

/**
 * 处理Get请求
 * @param url /开头，自动填充域名
 * @param role 身份 student teacher leader 秘书 admin
 * @param success(data) 成功回调函数
 * @param fail(data) 失败回调函数
 */
function handlerGet(url, role, success, fail) {
  var random = sessionStorage.getItem(mythos);
  if (random == null || random === '') {
    login(role)
  }
  var request = $.ajax({
    method: 'GET',
    url : host+''+url,
    contentType: "application/json",
    headers:{
      'Authorization' : 'Mythos '+random
    }
  });
  request.done(success);
  request.fail(fail);
}
