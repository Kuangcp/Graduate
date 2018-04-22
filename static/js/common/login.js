function login(role) {
  layer.prompt({
    formType: 0,
    value: '1',
    title: '请输入用户名'
  }, function (value1, index, elem) {
    layer.close(index);
    layer.prompt({
      formType: 1,
      value: '1',
      title: '请输入密码'
    }, function (value2, index, elem) {
      layer.close(index);
      var data = {
        username:'"'+value1+'"',
        password:'"'+value2+'"',
        role:'"'+role+'"'
      };
      data = JSON.stringify(data);
      $.post(host + '/login', data, function (data) {
        console.log(data);
        if (data.code !== 0) {
          login()
        }else{
          sessionStorage.setItem("", "");
        }
      }, "json");
    });
  });
}