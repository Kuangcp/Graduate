// 登录页面的公共JS

function readParamter(type) {
    var url = location.search;
    if (url.indexOf("?") !== -1) {
        url = url.split("?")[1]
        console.log(url)
    }else{
        //原意是想通过这个进行刷新页面
        // window.location.href = "/myth/"+type+"/login?error=true"
    }

}

function alertError() {
    var url = location.search;
    if (url.indexOf("?") !== -1) {
        layer.open({
            type: 0,
            shade: 0,
            closeBtn: 0,
            title: '',
            time: 2000,
            offset: 'rt',
            content: '用户名或者密码错误'
        })
    }
}

function login(name, type) {
    layer.open({
        type: 1,
        title: ''+name,
        shade: 0,
        closeBtn: 0,
        moveOut: true,
        resize: false,
        content: '<form class="form-signin" action="/myth/signin/'+type+'" method="post">\n' +
        // '        <h2 class="form-signin-heading" onclick="login()">Please sign in</h2>\n' +
        // '        <label for="inputEmail" class="sr-only">Email address</label>\n' +
        '        <input type="text" name="name" class="form-control" placeholder="用户名/id" required="required" autofocus="autofocus" value="1"/>\n' +
        // '        <label for="inputPassword" class="sr-only">Password</label>\n' +
        '        <input type="password" name="password" class="form-control" placeholder="密码" required="required" value="1"/>\n' +
        // '        <div class="checkbox">\n' +
        // '            <label>\n' +
        // '                <input type="checkbox" value="remember-me" />\n' +
        // '                 Remember me\n' +
        // '            </label>\n' +
        // '        </div>\n' +
        '        <button class="btn btn-lg btn-primary" type="submit" style="margin-left: 33%;">登录</button>\n' +
        '    </form>' //这里content是一个普通的String
    });
}