## TODO

- 业务规则的设计
- 优化默认学生,外键的问题,方便获取数据,但是难调整数据

### 问题
- 数据库设计对于外键，DO对象肯定是要引用对象的，那么JPA的入参也是对象，但是实际上参数传输都是id， 那么在进行调用JPA的时候
    - 就是实例化只有id属性的对应DO，内存不会泄露？

- 还是外键的问题，MVC在将对象转换为JSON时，会递归的将引用对象解析成JSON，这样对于一对多很容易出现死递归
    - 而且当一个多方对象集合，要转换成JSON就会发现里面的一方是冗余了的，而且是大量冗余！！
	
- 课题上直接放上学生的ID，该冗余是否合理？要去处理默认ID的事情，不过这样能方便快速查询课题对应的学生而已

- 数据的导入问题。 学生，教师，学院，专业，等一系列数据怎么导入？

- 排查打包中的MVC问题，将模板引擎彻底抛弃，重构项目！

- Data rest 格式能自定义么


### 技术选型
- 数据的操作框架 还是用JPA么，还是使用Mybatis结合插件进行开发（性能问题，还是说又是因为关联问题导致的JSON化产生的冗余）


springboot+postgresql+docker实例
https://segmentfault.com/a/1190000005172960


SpringBoot集成mybatis
https://segmentfault.com/a/1190000004275305?_ea=556953


<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>备份Mysql</title>
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="shortcut icon" href="/image/favicon.ico"/>
    <style>
        .title {
            text-align: center;
            margin-left: -100px;
            margin-top: 10px;
            margin-bottom: 20px;
        }

        .inputArea input {
            width: 140px;
            margin-right: 10px;
            margin-bottom: 10px;
        }

        .selectArea {
            text-align: center;
        }

        .choose {
            font-size: 18px;
            text-align: center;
            margin: 3px 4px;
        }
    </style>
</head>
<body>
<nav class="title">
    <input name="value" id="value" style="width: 400px;height: 36px;"/>
    <button onclick="searchReload()" class="layui-btn layui-btn-normal">搜索</button>
    <button class="layui-btn" onclick="add()">新增</button>
    <a href="">
        <button class="layui-btn layui-btn-warm">刷新页面</button>
    </a>
</nav>
<table id="demo" lay-filter="test"></table>
<script src="/layui/layui.js"></script>
<script src="/js/jquery-1.8.0.min.js"></script>
<script>
  sessionStorage.setItem('tables', '');
  // 表格组件
  layui.use('table', function () {
    var table = layui.table;
    table.render({
      elem: '#demo'
      , height: 710
      // ,width: 900
      , limit: 16
      , limits: [16, 32, 48]
      , skin: 'nob'
      , url: '/mysql/target/all' //数据接口
      , page: true //开启分页   
      , cols: [[ //表头
        {field: 'host', title: '主机', width: 160, sort: true}
        , {field: 'port', title: '端口', width: 120, sort: true}
        , {field: 'username', title: '用户名', width: 160}
        , {field: 'password', title: '密码', width: 160}
        , {field: 'databaseName', title: '数据库', width: 160}
        , {field: 'tableName', title: '备份的表', width: 120}
        , {
          field: 'includeFlag', title: '引入/排除', width: 100, align: 'center', templet: function (data) {
            if (data.includeFlag === '0') {
              return "排除"
            } else {
              return "引入"
            }
          }
        }
        , {
          field: 'disable', title: '启用/禁用', width: 100, align: 'center', templet: function (data) {
            if (data.disable === '0') {
              return "启用"
            } else {
              return "禁用"
            }
          }
        }
        , {
          field: 'structure', title: '结构/结构+数据', width: 160, align: 'center', templet: function (data) {
            if (data.structure === '0') {
              return "结构+数据"
            } else {
              return "结构"
            }
          }
        }
        , {field: 'comment', title: '备注', width: 200}
        // ,{field: 'uuid', title: 'uuid', width:160,hidden:true}
        , {field: 'oid', title: '操作', width: 80, toolbar: '#barDemo'}
      ]]
    });

    //监听工具条
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      // console.log(data);
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      // var tr = obj.tr; //获得当前行 tr 的DOM对象
      // console.log(data.uuid);
      var main = $("#editLine").html().replace('#####', data.uuid);
      if (layEvent === 'edit') { //编辑
        $.get('/mysql/target/one/' + data.uuid, function (data) {
          sessionStorage.setItem('tables', data.data.tableName);
          main = main.replace('id="host" value=""', 'id="host" value="' + data.data.host + '"');
          main = main.replace('id="port" value=""', 'id="port" value="' + data.data.port + '"');
          main = main.replace('id="username" value=""', 'id="username" value="' + data.data.username + '"');
          main = main.replace('id="password" value=""', 'id="password" value="' + data.data.password + '"');
          main = main.replace('id="database_name" value=""', 'id="database_name" value="' + data.data.databaseName + '"');
          main = main.replace('id="comment"  value=""', 'id="comment"  value="' + data.data.comment + '"');
          if (data.data.disable === '1') {
            main = main.replace('<option value="1">禁用备份</option>', '<option value="1" selected>禁用备份</option>');
            main = main.replace('<option value="0" selected>启用备份</option>', '<option value="0" >启用备份</option>');
          }
          if (data.data.includeFlag === '0') {
            main = main.replace('<option value="1" selected>引入下列表</option>', '<option value="1" >引入下列表</option>');
            main = main.replace('<option value="0">排除下列表</option>', '<option value="0" selected>排除下列表</option>');
          }
          if (data.data.structure === '0') {
            main = main.replace('<option value="1" selected>结构</option>', '<option value="1">结构</option>');
            main = main.replace('<option value="0">结构+数据</option>', '<option value="0" selected>结构+数据</option>');
          }
          tips(main);
        });
      }
    });
  });

  function reload() {
    layui.use('table', function () {
      var table = layui.table;
      table.reload('demo', {
        url: '/mysql/target/all'
      });
    })
  }

  function searchReload() {
    var value = $("#value").val();
    if (value === '' || value === ' ') {
      layer.msg('请输入查询参数');
      return;
    }
    value = value.replaceAll("%", '');
    layui.use('table', function () {
      var table = layui.table;
      table.reload('demo', {
        url: '/mysql/target/search/allField/' + value
        , where: {} //设定异步数据接口的额外参数
        //,height: 300
      });
    })
  }

  function requireValue(name) {
    var result = $("#" + name).val();
    if (result === null || result === '' || result === 0) {
      alert('请补全 ' + name + ' 参数');
      exit(1)
    } else {
      return result;
    }
  }

  function getValue(name) {
    return $("#" + name).val()
  }

  function showTables() {
    var dataTip = $("#Tips");
    var uuid = dataTip.attr('data-uuid');
    // console.log(uuid);

    var postData = {
      uuid: uuid,
      host: requireValue('host'),
      port: requireValue('port'),
      username: requireValue('username'),
      password: requireValue('password'),
      comment: getValue('comment'),
      databaseName: requireValue('database_name'),
      disable: getValue('disable'),
      includeFlag: getValue('include_flag'),
      structure: getValue('structure')
    };
    $.post("/mysql/target/add", postData, function (data) {
      if (data.code === '0') {
        dataTip.attr('data-uuid', data.data);
        $.get('/mysql/target/tables/' + data.data, function (data) {
          if (data.code === '0') {
            // console.log('列出表格 '+data);
            var tableName = sessionStorage.getItem('tables');
            var tableArea = $("#tableArea");
            tableArea.html('');
            tableArea.attr('data-status', true);
            var result = data.data.substr(0, data.data.length - 1).split("#");
            result.sort();
            result.forEach(function (item) {
              if (tableName.split(item + '#').length > 1) {
                $("#tableArea").append('<button id="' + item + '" class="choose layui-btn layui-btn-xs layui-btn-normal " onclick="choose(\'' + item + '\')" data-status="1" >' + item + '</button><br/>');
              } else {
                $("#tableArea").append('<button id="' + item + '" class="choose layui-btn layui-btn-xs layui-btn-primary" onclick="choose(\'' + item + '\')" data-status="0" >' + item + '</button><br/>');
              }
            });
          } else {
            alert('参数配置有误, 获取表格失败')
          }
        });
      } else {
        if (data.msg === 'repeat') {
          alert('该记录已存在，请重试');
        } else {
          alert('遇到错误，请重试')
        }
      }
    });
  }

  function choose(item) {
    item = $("#" + item);
    var status = item.data('status');
    // console.log(item.attr('id') + ' | ' + status);
    if (status === 0) {
      item.data('status', 1);
      item.attr('class', 'choose layui-btn layui-btn-xs layui-btn-normal');
      sessionStorage.setItem('tables', sessionStorage.getItem('tables') + item.attr('id') + '#');
    } else {
      item.data('status', 0);
      item.attr('class', 'choose layui-btn layui-btn-xs layui-btn-primary');
      var temp = sessionStorage.getItem('tables');
      temp = temp.split(item.attr('id') + '#');
      sessionStorage.setItem('tables', temp[0] + temp[1]);
    }
  }

  function saveTables() {
    var status = $("#tableArea").data('status');
    if (status === true) {
      var uuid = $("#Tips").data('uuid');
      var tables = sessionStorage.getItem('tables');
      $.post('/mysql/target/table/' + uuid, {
        tableName: tables
      }, function (data) {
        if (data.code === '0') {
          layer.msg('更新成功')
        } else {
          alert('更新备份的表失败')
        }
        sessionStorage.setItem('tables', '')
      })
    }
  }

  function add() {
    tips($("#editLine").html());
  }

  function tips(content) {
    layer.open({
      title: '选择数据库和表格'
      , content: content
      , btnAlign: 'c'
      , resize: true
      , area: ['450px', '600px']
      , btn: ['保存配置', '取消']
      , yes: function (index, layero) {
        showTables();
        reload();
        saveTables();
        layer.msg('配置保存成功')
      },
      cancel: function () {
        sessionStorage.setItem('tables', '')
      }
    });
  }
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>

<script id="editLine" type="text/html">
    <div class="inputArea" data-uuid="#####" id="Tips">
        <label for="host">主机IP</label>
        <input name="host" type="text" id="host" value="" required/>
        <label for="port">端口</label>
        <input name="port" type="number" id="port" value="" width="60px" required/><br/>
        <label for="username">用户名</label>
        <input name="username" type="text" id="username" value="" required/>
        <label for="password">密码</label>
        <input name="password" type="text" id="password" value="" required/><br/>
        <label for="database_name">数据库</label>
        <input name="databaseName" type="text" id="database_name" value="" style="width: 350px" required/><br/>
        <div style="text-align: center;margin-bottom: 10px;">
            <select name="includeFlag" id="include_flag">
                <option value="1" selected>引入下列表</option>
                <option value="0">排除下列表</option>
            </select>
            <select name="structure" id="structure">
                <option value="1" selected>结构</option>
                <option value="0">结构+数据</option>
            </select>
            <select name="disable" id="disable">
                <option value="0" selected>启用备份</option>
                <option value="1">禁用备份</option>
            </select>
        </div>
        <label for="comment">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
        <input name="comment" type="text" id="comment" value="" style="width: 350px"/>
    </div>
    <div class="selectArea">
        <button class="layui-btn layui-btn-xs layui-btn-normal " style="margin: 20px 0" onclick="showTables()"
                id="listButton">使用以上参数列出所有表格
        </button>
        <div class="check1" style="text-align: left;">
            <div id="tableArea" data-status="false"></div>
        </div>
    </div>
</script>
</body>
</html>