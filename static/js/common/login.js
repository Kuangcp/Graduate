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
      console.log('发哦给你  '+data)
      $.post(host + '/login', data, function (data) {
        console.log(data);
        if (data.code === '0' ) {
          sessionStorage.setItem("randomValue", data.data);
          layer.msg('登录成功 ')
        }else{
          login(role);
          layer.msg('用户名或密码错误')
        }
      }, "json");
    });
  });
}