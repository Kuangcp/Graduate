/**
 * 输入节点ID和执行的方法，即可enter提交
 * @param nodeName
 * @param target
 */
function enterSubmit(nodeName, target) {
    $("#"+nodeName).on('keydown', function (e) {
        // console.log(e.keyCode)
        if (e.keyCode === 13){
            target()
        }
    })
}
// 选课题
function selectTopic(topicId) {
    layer.open({
        type: 1,
        title: '选择该课题',
        area: ['320px', '220px'],
        resize: false,
        content: '<div><textarea name="comment" id="submitComment" style="width: 300px;height: 120px;border: none;margin: 1px 0 5px 10px;" placeholder="请输入你想发送的消息"/><br/>' +
        '<button type="button" onclick="select('+topicId+')" class="btn btn-success" style="margin-left: 32%;">确定选择</button></div>'
    })
}
function select(topicId) {
    layer.closeAll('page')
    var coment= $("#submitComment").val()
    $.get('/myth/student/ChooseTopic/s/'+topicId+'?comment='+coment, function (data) {
        if('Fail' === data){
            layer.msg('选题失败,请刷新页面重试')
        }else if('Already' === data){
            layer.msg('你已经选了该课题')
        }else{
            layer.msg('选题成功，请等待对应教师回复')
        }
    })
}
