function openTips1() {
    layer.open({
        type: 1,
        title: '查看教师回复',
        area: ['320px', '220px'],
        resize: false,
        content: '<div><textarea name="comment" id="submitComment" style="width: 300px;height: 120px;border: none;margin: 1px 0 5px 10px;" placeholder="请输入你想发送的消息"/><br/>' +
        '<button type="button" onclick="select(' + topicId + ')" class="btn btn-success" style="margin-left: 32%;">确定选择</button></div>'
    })
}